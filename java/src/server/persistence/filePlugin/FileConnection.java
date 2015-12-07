/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.persistence.filePlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.persistence.DAO.IConnections;

/**
 *
 */
public class FileConnection implements IConnections {
    
    private boolean inTransaction = false;
    
    private File inputGamesFile, outputGamesFile, inputCommandsFile, outputCommandsFile;
    
    private final String defaultGamesFile = "persistence/games.dat";
    private final String defaultCommandsFile = "persistence/commands.dat";
    
    private final String tempOutputSuffix = ".tmp";
    
    private final String tempOldFileSuffix = ".old";
    
    public FileConnection() {
        initFileNames();
    }
        
    private void initFileNames() {
        this.inputGamesFile = new File( defaultGamesFile );
        this.outputGamesFile = this.inputGamesFile;
        this.inputCommandsFile = new File( defaultCommandsFile );
        this.outputCommandsFile = this.inputCommandsFile;
    }

    @Override
    public void startTransaction() {
        this.outputGamesFile = getTempFile( this.inputGamesFile.getAbsolutePath() );
        this.outputCommandsFile = getTempFile( this.inputCommandsFile.getAbsolutePath() );
        
        // Write new temp outputCommandsFile with current contents of inputCommandsFile
        // so that append() can be used
        writeCommandFile(getCommandsString());
        
        this.inTransaction = true;
    }
    
    private File getTempFile( String name ) {
        File ret = new File( name + tempOutputSuffix );
        ret.deleteOnExit();
        return ret;
    }
    
    private boolean isTempFile( File f ) {
        return f.getName().endsWith(tempOutputSuffix);
    }
    
    private boolean isOldFile( File f ) {
        return f.getName().endsWith(tempOldFileSuffix);
    }
    
    private void deleteOldFile( File f ) throws IOException {
        if ( !isOldFile(f) ) {
            f = new File( f.getAbsolutePath() + tempOldFileSuffix );
        }
        deleteFile(f);
    }
    
    private void deleteFile( File f ) throws IOException {
        boolean success = true;
        success = f.delete();
        if ( ! success ) 
            throw new IOException("File " + f.toString() + " was not deleted!");
    }
    
    private void deleteTempFile( File f ) throws IOException {
        if ( isTempFile( f ) )
            deleteFile( f );
    }
    
    private void renameOld( File f ) throws IOException {
        boolean success = f.renameTo(new File( f.getAbsolutePath() + tempOldFileSuffix ) );
        if ( ! success ) {
            throw new IOException("File could not be renamed!");
        }
    }
    
    private void renameUnTemp( File f ) throws IOException {
        if ( ! isTempFile(f) )
            throw new IOException("The selected file is not a temp file!");
        
        boolean success = f.renameTo(new File( f.getAbsolutePath().substring(0, f.getAbsolutePath().indexOf(tempOutputSuffix)) ) );
        if ( ! success ) {
            throw new IOException("File could not be renamed!");
        }
    }

    @Override
    public void endTransaction() {
        if ( ! inTransaction )
            throw new IllegalStateException( "No transaction to commit!" );
        
        try {
            // Rename old input files
            renameOld( this.inputCommandsFile );
            renameOld( this.inputGamesFile );
            // Rename temp files so they're the new input files
            renameUnTemp( this.outputCommandsFile );
            renameUnTemp( this.outputGamesFile );
            // Delete old input files
            deleteOldFile(this.inputGamesFile);
            deleteOldFile(this.inputCommandsFile);
            
            initFileNames();
            
            this.inTransaction = false;
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollBack() {
        if ( ! inTransaction ) {
            throw new IllegalStateException("no transaction to roll back!");
        }
        
        // Delete temp files if they're temp
        try {
            deleteTempFile( this.outputGamesFile );
            deleteTempFile( this.outputCommandsFile );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        // Set output files back to being input files
        initFileNames();
        
        this.inTransaction = false;
    }

    @Override
    public void openConnection() {
    }

    @Override
    public void closeConnection() {
    }
    
    private byte[] readBytes( File f ) {
        try {
            byte[] array = Files.readAllBytes( f.toPath() );
            return array;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public byte[] getGamesBytes() {
        return readBytes(this.inputGamesFile);
    }
    
    public String getCommandsString() {
        return new String( readBytes( this.inputCommandsFile ) );
    }
    
    public void writeGamesBytes( byte[] b ) {
        try {
            Files.write( this.outputGamesFile.toPath(), b );
        } catch (IOException ex) {
            Logger.getLogger(FileConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void appendCommandString( String s ) {
        try {
            // This could be slow because we're opening and closing the file each time we write to it.
            Files.write( this.outputCommandsFile.toPath(), s.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException ex) {
            Logger.getLogger(FileConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void writeCommandFile( String s ) {
        try {
            Files.write( this.outputCommandsFile.toPath(), s.getBytes() );
        } catch (IOException ex) {
            Logger.getLogger(FileConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void clearFile( File f ) {
        try {
            FileChannel outChan = new FileOutputStream(f).getChannel();
            outChan.truncate(0);
            outChan.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
    
    public void clearGamesFile() {
        clearFile(outputGamesFile);
    }
    
    public void clearCommandsFile() {
        clearFile(outputCommandsFile);
    }
}

package client.facade;

import client.proxy.IServerProxy;
import shared.model.Model;

/**
 *
 * @author JanPaul
 */
public class CanDoFacadeMyTurn extends CanDoFacade {
    
   public CanDoFacadeMyTurn(IServerProxy proxy, Model model) {
       super( proxy, model );
   }
}

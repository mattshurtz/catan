/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.communication.params;

/**
 *
 * @author JanPaul
 */
public class AddAiRequest {
    private String AIType;

    public AddAiRequest(String string) {
		this.AIType = string;
	}

	public String getAIType() {
        return AIType;
    }

    public void setAIType(String AIType) {
        this.AIType = AIType;
    }
}

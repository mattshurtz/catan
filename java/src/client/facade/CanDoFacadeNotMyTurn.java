package client.facade;

import client.proxy.IServerProxy;
import shared.model.Model;

/**
 *
 * @author JanPaul
 */
public class CanDoFacadeNotMyTurn extends CanDoFacade {
    public CanDoFacadeNotMyTurn(IServerProxy proxy, Model model) {
       super( proxy, model );
   }
}

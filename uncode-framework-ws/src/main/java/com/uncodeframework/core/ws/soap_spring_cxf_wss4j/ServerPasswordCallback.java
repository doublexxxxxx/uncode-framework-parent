package com.uncodeframework.core.ws.soap_spring_cxf_wss4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.springframework.stereotype.Component;

@Component
public class ServerPasswordCallback implements CallbackHandler {

    private static final Map<String, String> userMap = new HashMap<String, String>();

    static {
        userMap.put("client", "clientpass");
        userMap.put("server", "serverpass");
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback callback = (WSPasswordCallback) callbacks[0];

        String clientUsername = callback.getIdentifier();
        String serverPassword = userMap.get(clientUsername);

        if (serverPassword != null) {
            callback.setPassword(serverPassword);
        }
    }
}

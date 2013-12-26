package it.polimi.dmw.cac.explore.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

public abstract class RestContainer {

    private static final String SESSION_USERNAME = "session_username";

    @Context
    private HttpServletRequest request;

    protected HttpServletRequest getRequest() {
        return request;
    }

    protected void setSessionUsername(String username) {
        assertRequestNotNull();
        request.getSession().setAttribute(SESSION_USERNAME, username);

    }

    protected String getSessionUsername() {
        assertRequestNotNull();
        return (String) request.getSession().getAttribute(SESSION_USERNAME);
    }

    private void assertRequestNotNull() {
        if (request == null) {
            throw new IllegalStateException("Request is null");
        }
    }

}

package controllers;

import request.HTTPRequest;
import response.HTTPResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import static response.StatusLine.OK;

public class ParameterPage extends Controller {

    @Override
    public HTTPResponse get(HTTPRequest request) {
        return new HTTPResponse(OK, buildBodyWith(decodedParameters(request)));
    }

    private Map<String, String> decodedParameters(HTTPRequest request) {
        Map<String, String> decodedParameters = new HashMap<>();
        if (containsMoreParameters(request)) {
            decodeAllParameters(request, decodedParameters);
        } else {
            decodeParameter(decodedParameters, request.getQueryString());
        }
        return decodedParameters;
    }

    private String buildBodyWith(Map<String, String> decodedParameters) {
        StringBuilder responseBody = new StringBuilder();
        for (Map.Entry<String, String> decodedParameter : decodedParameters.entrySet()) {
            responseBody.append(decodedParameter.getKey() + " = " + decodedParameter.getValue() + "\n");
        }
        return responseBody.toString();
    }

    private boolean containsMoreParameters(HTTPRequest request) {
        return request.getQueryString().contains("&");
    }

    private void decodeAllParameters(HTTPRequest request, Map<String, String> decodedParameters) {
        String[] parameters = request.getQueryString().split("&");
        for (String parameter : parameters) {
            decodeParameter(decodedParameters, parameter);
        }
    }

    private void decodeParameter(Map<String, String> decodedParameters, String parameter) {
        String[] splitKeyAndValue = parameter.split("=");
        try {
            decodedParameters.put(splitKeyAndValue[0], URLDecoder.decode(splitKeyAndValue[1], "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

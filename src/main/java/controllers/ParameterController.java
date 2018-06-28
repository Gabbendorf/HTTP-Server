package controllers;

import exceptions.NotSupportedEncodingException;
import request.HTTPRequest;
import response.HTTPResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

import static response.StatusLine.OK;

public class ParameterController extends Controller {

    @Override
    public HTTPResponse get(HTTPRequest request) {
        return new HTTPResponse(OK, buildBodyWith(parametersDecoded(request)));
    }

    private String buildBodyWith(Map<String, String> parametersDecoded) {
        StringBuilder responseBody = new StringBuilder();
        for (Map.Entry<String, String> parameterDecoded: parametersDecoded.entrySet()) {
            responseBody.append(parameterDecoded.getKey() + " = " + parameterDecoded.getValue() + "\n");
        }
        return responseBody.toString();
    }

    private boolean containsMoreParameters(HTTPRequest request) {
        return request.getQueryString().contains("&");
    }

    private Map<String, String> parametersDecoded(HTTPRequest request) {
        Map<String, String> parametersDecoded = new HashMap<>();
        for (String parameter : parameters(request)) {
            String[] splitKeyAndValue = parameter.split("=");
            parametersDecoded.put(splitKeyAndValue[0], decode(splitKeyAndValue[1]));
        }
        return parametersDecoded;
    }

    private List<String> parameters(HTTPRequest request) {
        List<String> parameters = new ArrayList<>();
        if (containsMoreParameters(request)) {
            Collections.addAll(parameters, request.getQueryString().split("&"));
        } else {
            parameters.add(request.getQueryString());
        }
        return parameters;
    }

    private String decode(String parameter) {
        String decodedParameter;
        try {
            decodedParameter = URLDecoder.decode(parameter, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new NotSupportedEncodingException(e);
        }
        return decodedParameter;
    }
}

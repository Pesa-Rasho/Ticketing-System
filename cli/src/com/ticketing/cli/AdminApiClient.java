package com.ticketing.cli;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The AdminApiClient class handles HTTP requests to the administrative API endpoints.
 * It provides methods to start and stop the system.
 */
public class AdminApiClient {
    /**
     * The base URL for the administrative API.
     */
    private static final String BASE_URL = "http://localhost:8081/api/admin";

    /**
     * Sends a POST request to the "start" endpoint to start the system.
     *
     * @return True if the system started successfully, false otherwise.
     */
    public boolean startSystem() {
        return makeSimplePostRequest("/start");
    }

    /**
     * Sends a POST request to the "stop" endpoint to stop the system.
     *
     * @return True if the system stopped successfully, false otherwise.
     */
    public boolean stopSystem() {
        return makeSimplePostRequest("/stop");
    }

    /**
     * Makes a simple POST request to the specified endpoint.
     *
     * @param endpoint The endpoint to which the POST request is sent.
     * @return True if the request was successful (HTTP 200 OK), false otherwise.
     */
    private boolean makeSimplePostRequest(String endpoint) {
        try {
            URL url = new URL(BASE_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            int responseCode = connection.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK;

        } catch (Exception e) {
            System.out.println("Error during API call to " + endpoint + ": " + e.getMessage());
            return false;
        }
    }
}

package io.github.c4x2.jtinysdk.service;

import io.github.c4x2.jtinysdk.client.HttpTransport;
import io.github.c4x2.jtinysdk.model.bulk.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * Service for batch operations on TinyURLs.
 */
public class BulkService {

    private final HttpTransport transport;

    public BulkService(HttpTransport transport) {
        this.transport = Objects.requireNonNull(transport, "transport must not be null");
    }

    /**
     * Submit a new TinyURL processing batch.
     *
     * @param request the batch request containing items to process
     * @return response with batch ID and initial status
     */
    public BulkStatusResponse create(BulkRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        return transport.send("POST", "/bulk", null, request, BulkStatusResponse.class);
    }

    /**
     * Convenience method to submit a list of bulk items under a default domain.
     *
     * @param domain default domain for items
     * @param items  list of items to process
     * @return response with batch ID and initial status
     */
    public BulkStatusResponse create(String domain, List<BulkRequestItem> items) {
        return create(new BulkRequest(domain, items));
    }

    /**
     * Convenience method to submit a varargs array of bulk items.
     *
     * @param items items to process
     * @return response with batch ID and initial status
     */
    public BulkStatusResponse create(BulkRequestItem... items) {
        return create(new BulkRequest(Arrays.asList(items)));
    }

    /**
     * Asynchronously submit a new TinyURL processing batch.
     *
     * @param request the batch request
     * @return a CompletableFuture holding the bulk status response
     */
    public CompletableFuture<BulkStatusResponse> createAsync(BulkRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        return transport.sendAsync("POST", "/bulk", null, request, BulkStatusResponse.class);
    }

    /**
     * Check the status and progress of a bulk request.
     *
     * @param bulkRequestId the ID of the bulk batch
     * @return response containing current status and counters (total, success, failed)
     */
    public BulkStatusResponse getStatus(long bulkRequestId) {
        String path = "/bulk/" + bulkRequestId + "/status";
        return transport.send("GET", path, null, null, BulkStatusResponse.class);
    }

    /**
     * Asynchronously check the status of a bulk request.
     *
     * @param bulkRequestId the ID of the bulk batch
     * @return a CompletableFuture holding the bulk status response
     */
    public CompletableFuture<BulkStatusResponse> getStatusAsync(long bulkRequestId) {
        String path = "/bulk/" + bulkRequestId + "/status";
        return transport.sendAsync("GET", path, null, null, BulkStatusResponse.class);
    }

    /**
     * Retrieve the detailed report for a completed bulk request.
     *
     * @param bulkRequestId the ID of the bulk batch
     * @return response containing report items for each processed link
     */
    public BulkReportResponse getReport(long bulkRequestId) {
        String path = "/bulk/" + bulkRequestId + "/report";
        return transport.send("GET", path, null, null, BulkReportResponse.class);
    }

    /**
     * Asynchronously retrieve the detailed report for a bulk request.
     *
     * @param bulkRequestId the ID of the bulk batch
     * @return a CompletableFuture holding the bulk report response
     */
    public CompletableFuture<BulkReportResponse> getReportAsync(long bulkRequestId) {
        String path = "/bulk/" + bulkRequestId + "/report";
        return transport.sendAsync("GET", path, null, null, BulkReportResponse.class);
    }
}


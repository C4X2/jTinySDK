package io.github.c4x2.jtinysdk.model.bulk;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Status data for a bulk request batch.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BulkStatusData {

    public static final int STATUS_NEW = 0;
    public static final int STATUS_PROCESSING = 1;
    public static final int STATUS_PROCESSED = 2;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("success")
    private Integer success;

    @JsonProperty("failed")
    private Integer failed;

    public BulkStatusData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getFailed() {
        return failed;
    }

    public void setFailed(Integer failed) {
        this.failed = failed;
    }

    public boolean isNew() {
        return status != null && status == STATUS_NEW;
    }

    public boolean isProcessing() {
        return status != null && status == STATUS_PROCESSING;
    }

    public boolean isProcessed() {
        return status != null && status == STATUS_PROCESSED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BulkStatusData that = (BulkStatusData) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(total, that.total) &&
                Objects.equals(success, that.success) &&
                Objects.equals(failed, that.failed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, createdAt, updatedAt, total, success, failed);
    }

    @Override
    public String toString() {
        return "BulkStatusData{" +
                "id=" + id +
                ", status=" + status +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", total=" + total +
                ", success=" + success +
                ", failed=" + failed +
                '}';
    }
}


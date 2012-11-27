package jp.caliconography.kms.model;

import java.io.Serializable;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;

@Model(schemaVersion = 1)
public class ProcessResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    public enum ProcessStatus {
    	SUCCESS,
    	ERROR;
    };
    
    private ProcessStatus status;
    
    private String message;
    
    private int processCount;
    
    
    public ProcessResult() {
		super();
	}

	public ProcessResult(ProcessStatus status, String message, int processCount) {
		super();
		this.status = status;
		this.message = message;
		this.processCount = processCount;
	}

	/**
     * Returns the key.
     *
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key
     *            the key
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Returns the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version
     *            the version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ProcessResult other = (ProcessResult) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

	/**
	 * @return status
	 */
	public ProcessStatus getStatus() {
		return status;
	}

	/**
	 * @param status セットする status
	 */
	public void setStatus(ProcessStatus status) {
		this.status = status;
	}

	/**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message セットする message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return processCount
	 */
	public int getProcessCount() {
		return processCount;
	}

	/**
	 * @param processCount セットする processCount
	 */
	public void setProcessCount(int processCount) {
		this.processCount = processCount;
	}
}

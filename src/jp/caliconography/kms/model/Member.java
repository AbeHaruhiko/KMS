package jp.caliconography.kms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.json.Json;

import com.google.appengine.api.datastore.Key;

@Model(schemaVersion = 1)
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    @Json(ignore=true)
    private Key key;

    @Attribute(version = true)
    @Json(ignore=true)
    private Long version;

    private String gplusId;
    
    private String gplusName;
    
    private String shussekiBango;

    private String twitterId;
    
    private String shusshinchi;
    
    private String genzaichi;
    
    private String pr;
    
    private String kumi;
    
    private String oshi;
    
    private String memo;
    
    private boolean isApproved;

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
        Member other = (Member) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }
    
    public List<String> getAttributeList() {
    	
    	List<String> list = new ArrayList<String>();
    	list.add(gplusId);
    	list.add(gplusName);
    	list.add(twitterId);
    	list.add(kumi);
    	list.add(oshi);
    	list.add(pr);
    	list.add(memo);
    	
    	return list;
    }

    /**
     * @return gplusId
     */
    public String getGplusId() {
        return gplusId;
    }

    /**
     * @param gplusId セットする gplusId
     */
    public void setGplusId(String gplusId) {
        this.gplusId = gplusId;
    }

    /**
     * @return gplusName
     */
    public String getGplusName() {
        return gplusName;
    }

    /**
     * @param gplusName セットする gplusName
     */
    public void setGplusName(String gplusName) {
        this.gplusName = gplusName;
    }

    /**
     * @return shussekiBango
     */
    public String getShussekiBango() {
        return shussekiBango;
    }

    /**
     * @param shussekiBango セットする shussekiBango
     */
    public void setShussekiBango(String shussekiBango) {
        this.shussekiBango = shussekiBango;
    }

    /**
     * @return twitterId
     */
    public String getTwitterId() {
        return twitterId;
    }

    /**
     * @param twitterId セットする twitterId
     */
    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    /**
     * @return shusshinchi
     */
    public String getShusshinchi() {
        return shusshinchi;
    }

    /**
     * @param shusshinchi セットする shusshinchi
     */
    public void setShusshinchi(String shusshinchi) {
        this.shusshinchi = shusshinchi;
    }

    /**
     * @return genzaichi
     */
    public String getGenzaichi() {
        return genzaichi;
    }

    /**
     * @param genzaichi セットする genzaichi
     */
    public void setGenzaichi(String genzaichi) {
        this.genzaichi = genzaichi;
    }

    /**
     * @return pr
     */
    public String getPr() {
        return pr;
    }

    /**
     * @param pr セットする pr
     */
    public void setPr(String pr) {
        this.pr = pr;
    }

    /**
     * @return kumi
     */
    public String getKumi() {
        return kumi;
    }

    /**
     * @param kumi セットする kumi
     */
    public void setKumi(String kumi) {
        this.kumi = kumi;
    }

    /**
     * @return oshi
     */
    public String getOshi() {
        return oshi;
    }

    /**
     * @param oshi セットする oshi
     */
    public void setOshi(String oshi) {
        this.oshi = oshi;
    }

    /**
	 * @return isApproved
	 */
	public boolean isApproved() {
		return isApproved;
	}

	/**
	 * @param isApproved セットする isApproved
	 */
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	/**
     * @return memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo セットする memo
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }
}

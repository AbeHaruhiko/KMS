package jp.caliconography.kms.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2012-12-08 14:11:49")
/** */
public final class ProcessResultMeta extends org.slim3.datastore.ModelMeta<jp.caliconography.kms.model.ProcessResult> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.caliconography.kms.model.ProcessResult, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<jp.caliconography.kms.model.ProcessResult, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.ProcessResult> message = new org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.ProcessResult>(this, "message", "message");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.caliconography.kms.model.ProcessResult, java.lang.Integer> processCount = new org.slim3.datastore.CoreAttributeMeta<jp.caliconography.kms.model.ProcessResult, java.lang.Integer>(this, "processCount", "processCount", int.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.caliconography.kms.model.ProcessResult, jp.caliconography.kms.model.ProcessResult.ProcessStatus> status = new org.slim3.datastore.CoreAttributeMeta<jp.caliconography.kms.model.ProcessResult, jp.caliconography.kms.model.ProcessResult.ProcessStatus>(this, "status", "status", jp.caliconography.kms.model.ProcessResult.ProcessStatus.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.caliconography.kms.model.ProcessResult, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<jp.caliconography.kms.model.ProcessResult, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final ProcessResultMeta slim3_singleton = new ProcessResultMeta();

    /**
     * @return the singleton
     */
    public static ProcessResultMeta get() {
       return slim3_singleton;
    }

    /** */
    public ProcessResultMeta() {
        super("ProcessResult", jp.caliconography.kms.model.ProcessResult.class);
    }

    @Override
    public jp.caliconography.kms.model.ProcessResult entityToModel(com.google.appengine.api.datastore.Entity entity) {
        jp.caliconography.kms.model.ProcessResult model = new jp.caliconography.kms.model.ProcessResult();
        model.setKey(entity.getKey());
        model.setMessage((java.lang.String) entity.getProperty("message"));
        model.setProcessCount(longToPrimitiveInt((java.lang.Long) entity.getProperty("processCount")));
        model.setStatus(stringToEnum(jp.caliconography.kms.model.ProcessResult.ProcessStatus.class, (java.lang.String) entity.getProperty("status")));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        jp.caliconography.kms.model.ProcessResult m = (jp.caliconography.kms.model.ProcessResult) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("message", m.getMessage());
        entity.setProperty("processCount", m.getProcessCount());
        entity.setProperty("status", enumToString(m.getStatus()));
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        jp.caliconography.kms.model.ProcessResult m = (jp.caliconography.kms.model.ProcessResult) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        jp.caliconography.kms.model.ProcessResult m = (jp.caliconography.kms.model.ProcessResult) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        jp.caliconography.kms.model.ProcessResult m = (jp.caliconography.kms.model.ProcessResult) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        jp.caliconography.kms.model.ProcessResult m = (jp.caliconography.kms.model.ProcessResult) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        jp.caliconography.kms.model.ProcessResult m = (jp.caliconography.kms.model.ProcessResult) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getMessage() != null){
            writer.setNextPropertyName("message");
            encoder0.encode(writer, m.getMessage());
        }
        writer.setNextPropertyName("processCount");
        encoder0.encode(writer, m.getProcessCount());
        if(m.getStatus() != null){
            writer.setNextPropertyName("status");
            encoder0.encode(writer, m.getStatus());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected jp.caliconography.kms.model.ProcessResult jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        jp.caliconography.kms.model.ProcessResult m = new jp.caliconography.kms.model.ProcessResult();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("message");
        m.setMessage(decoder0.decode(reader, m.getMessage()));
        reader = rootReader.newObjectReader("processCount");
        m.setProcessCount(decoder0.decode(reader, m.getProcessCount()));
        reader = rootReader.newObjectReader("status");
        m.setStatus(decoder0.decode(reader, m.getStatus(), jp.caliconography.kms.model.ProcessResult.ProcessStatus.class));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}
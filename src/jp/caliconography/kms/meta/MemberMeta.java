package jp.caliconography.kms.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2012-12-08 14:11:49")
/** */
public final class MemberMeta extends org.slim3.datastore.ModelMeta<jp.caliconography.kms.model.Member> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.caliconography.kms.model.Member, java.lang.Boolean> admin = new org.slim3.datastore.CoreAttributeMeta<jp.caliconography.kms.model.Member, java.lang.Boolean>(this, "admin", "admin", boolean.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.caliconography.kms.model.Member, java.lang.Boolean> approved = new org.slim3.datastore.CoreAttributeMeta<jp.caliconography.kms.model.Member, java.lang.Boolean>(this, "approved", "approved", boolean.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member> genzaichi = new org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member>(this, "genzaichi", "genzaichi");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member> gplusId = new org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member>(this, "gplusId", "gplusId");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member> gplusName = new org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member>(this, "gplusName", "gplusName");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.caliconography.kms.model.Member, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<jp.caliconography.kms.model.Member, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member> kumi = new org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member>(this, "kumi", "kumi");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member> mail = new org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member>(this, "mail", "mail");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member> memo = new org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member>(this, "memo", "memo");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member> oshi = new org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member>(this, "oshi", "oshi");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.caliconography.kms.model.Member, byte[]> passwordHash = new org.slim3.datastore.CoreAttributeMeta<jp.caliconography.kms.model.Member, byte[]>(this, "passwordHash", "passwordHash", byte[].class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member> pr = new org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member>(this, "pr", "pr");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.caliconography.kms.model.Member, byte[]> salt = new org.slim3.datastore.CoreAttributeMeta<jp.caliconography.kms.model.Member, byte[]>(this, "salt", "salt", byte[].class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member> shussekiBango = new org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member>(this, "shussekiBango", "shussekiBango");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member> shusshinchi = new org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member>(this, "shusshinchi", "shusshinchi");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member> twitterId = new org.slim3.datastore.StringAttributeMeta<jp.caliconography.kms.model.Member>(this, "twitterId", "twitterId");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.caliconography.kms.model.Member, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<jp.caliconography.kms.model.Member, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final MemberMeta slim3_singleton = new MemberMeta();

    /**
     * @return the singleton
     */
    public static MemberMeta get() {
       return slim3_singleton;
    }

    /** */
    public MemberMeta() {
        super("Member", jp.caliconography.kms.model.Member.class);
    }

    @Override
    public jp.caliconography.kms.model.Member entityToModel(com.google.appengine.api.datastore.Entity entity) {
        jp.caliconography.kms.model.Member model = new jp.caliconography.kms.model.Member();
        model.setAdmin(booleanToPrimitiveBoolean((java.lang.Boolean) entity.getProperty("admin")));
        model.setApproved(booleanToPrimitiveBoolean((java.lang.Boolean) entity.getProperty("approved")));
        model.setGenzaichi((java.lang.String) entity.getProperty("genzaichi"));
        model.setGplusId((java.lang.String) entity.getProperty("gplusId"));
        model.setGplusName((java.lang.String) entity.getProperty("gplusName"));
        model.setKey(entity.getKey());
        model.setKumi((java.lang.String) entity.getProperty("kumi"));
        model.setMail((java.lang.String) entity.getProperty("mail"));
        model.setMemo((java.lang.String) entity.getProperty("memo"));
        model.setOshi((java.lang.String) entity.getProperty("oshi"));
        model.setPasswordHash(shortBlobToBytes((com.google.appengine.api.datastore.ShortBlob) entity.getProperty("passwordHash")));
        model.setPr((java.lang.String) entity.getProperty("pr"));
        model.setSalt(shortBlobToBytes((com.google.appengine.api.datastore.ShortBlob) entity.getProperty("salt")));
        model.setShussekiBango((java.lang.String) entity.getProperty("shussekiBango"));
        model.setShusshinchi((java.lang.String) entity.getProperty("shusshinchi"));
        model.setTwitterId((java.lang.String) entity.getProperty("twitterId"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        jp.caliconography.kms.model.Member m = (jp.caliconography.kms.model.Member) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("admin", m.isAdmin());
        entity.setProperty("approved", m.isApproved());
        entity.setProperty("genzaichi", m.getGenzaichi());
        entity.setProperty("gplusId", m.getGplusId());
        entity.setProperty("gplusName", m.getGplusName());
        entity.setProperty("kumi", m.getKumi());
        entity.setProperty("mail", m.getMail());
        entity.setProperty("memo", m.getMemo());
        entity.setProperty("oshi", m.getOshi());
        entity.setProperty("passwordHash", bytesToShortBlob(m.getPasswordHash()));
        entity.setProperty("pr", m.getPr());
        entity.setProperty("salt", bytesToShortBlob(m.getSalt()));
        entity.setProperty("shussekiBango", m.getShussekiBango());
        entity.setProperty("shusshinchi", m.getShusshinchi());
        entity.setProperty("twitterId", m.getTwitterId());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        jp.caliconography.kms.model.Member m = (jp.caliconography.kms.model.Member) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        jp.caliconography.kms.model.Member m = (jp.caliconography.kms.model.Member) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        jp.caliconography.kms.model.Member m = (jp.caliconography.kms.model.Member) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        jp.caliconography.kms.model.Member m = (jp.caliconography.kms.model.Member) model;
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
        jp.caliconography.kms.model.Member m = (jp.caliconography.kms.model.Member) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        writer.setNextPropertyName("admin");
        encoder0.encode(writer, m.isAdmin());
        writer.setNextPropertyName("approved");
        encoder0.encode(writer, m.isApproved());
        if(m.getGenzaichi() != null){
            writer.setNextPropertyName("genzaichi");
            encoder0.encode(writer, m.getGenzaichi());
        }
        if(m.getGplusId() != null){
            writer.setNextPropertyName("gplusId");
            encoder0.encode(writer, m.getGplusId());
        }
        if(m.getGplusName() != null){
            writer.setNextPropertyName("gplusName");
            encoder0.encode(writer, m.getGplusName());
        }
        if(m.getKumi() != null){
            writer.setNextPropertyName("kumi");
            encoder0.encode(writer, m.getKumi());
        }
        if(m.getMail() != null){
            writer.setNextPropertyName("mail");
            encoder0.encode(writer, m.getMail());
        }
        if(m.getMemo() != null){
            writer.setNextPropertyName("memo");
            encoder0.encode(writer, m.getMemo());
        }
        if(m.getOshi() != null){
            writer.setNextPropertyName("oshi");
            encoder0.encode(writer, m.getOshi());
        }
        if(m.getPr() != null){
            writer.setNextPropertyName("pr");
            encoder0.encode(writer, m.getPr());
        }
        if(m.getShussekiBango() != null){
            writer.setNextPropertyName("shussekiBango");
            encoder0.encode(writer, m.getShussekiBango());
        }
        if(m.getShusshinchi() != null){
            writer.setNextPropertyName("shusshinchi");
            encoder0.encode(writer, m.getShusshinchi());
        }
        if(m.getTwitterId() != null){
            writer.setNextPropertyName("twitterId");
            encoder0.encode(writer, m.getTwitterId());
        }
        writer.endObject();
    }

    @Override
    protected jp.caliconography.kms.model.Member jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        jp.caliconography.kms.model.Member m = new jp.caliconography.kms.model.Member();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("admin");
        m.setAdmin(decoder0.decode(reader, m.isAdmin()));
        reader = rootReader.newObjectReader("approved");
        m.setApproved(decoder0.decode(reader, m.isApproved()));
        reader = rootReader.newObjectReader("genzaichi");
        m.setGenzaichi(decoder0.decode(reader, m.getGenzaichi()));
        reader = rootReader.newObjectReader("gplusId");
        m.setGplusId(decoder0.decode(reader, m.getGplusId()));
        reader = rootReader.newObjectReader("gplusName");
        m.setGplusName(decoder0.decode(reader, m.getGplusName()));
        reader = rootReader.newObjectReader("kumi");
        m.setKumi(decoder0.decode(reader, m.getKumi()));
        reader = rootReader.newObjectReader("mail");
        m.setMail(decoder0.decode(reader, m.getMail()));
        reader = rootReader.newObjectReader("memo");
        m.setMemo(decoder0.decode(reader, m.getMemo()));
        reader = rootReader.newObjectReader("oshi");
        m.setOshi(decoder0.decode(reader, m.getOshi()));
        reader = rootReader.newObjectReader("pr");
        m.setPr(decoder0.decode(reader, m.getPr()));
        reader = rootReader.newObjectReader("shussekiBango");
        m.setShussekiBango(decoder0.decode(reader, m.getShussekiBango()));
        reader = rootReader.newObjectReader("shusshinchi");
        m.setShusshinchi(decoder0.decode(reader, m.getShusshinchi()));
        reader = rootReader.newObjectReader("twitterId");
        m.setTwitterId(decoder0.decode(reader, m.getTwitterId()));
        return m;
    }
}
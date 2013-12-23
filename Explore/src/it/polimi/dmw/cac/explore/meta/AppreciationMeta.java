package it.polimi.dmw.cac.explore.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-12-23 13:27:04")
/** */
public final class AppreciationMeta extends org.slim3.datastore.ModelMeta<it.polimi.dmw.cac.explore.model.Appreciation> {

    /** */
    public final org.slim3.datastore.ModelRefAttributeMeta<it.polimi.dmw.cac.explore.model.Appreciation, org.slim3.datastore.ModelRef<it.polimi.dmw.cac.explore.model.User>, it.polimi.dmw.cac.explore.model.User> authorRef = new org.slim3.datastore.ModelRefAttributeMeta<it.polimi.dmw.cac.explore.model.Appreciation, org.slim3.datastore.ModelRef<it.polimi.dmw.cac.explore.model.User>, it.polimi.dmw.cac.explore.model.User>(this, "authorRef", "authorRef", org.slim3.datastore.ModelRef.class, it.polimi.dmw.cac.explore.model.User.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Appreciation, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Appreciation, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.ModelRefAttributeMeta<it.polimi.dmw.cac.explore.model.Appreciation, org.slim3.datastore.ModelRef<it.polimi.dmw.cac.explore.model.Review>, it.polimi.dmw.cac.explore.model.Review> reviewRef = new org.slim3.datastore.ModelRefAttributeMeta<it.polimi.dmw.cac.explore.model.Appreciation, org.slim3.datastore.ModelRef<it.polimi.dmw.cac.explore.model.Review>, it.polimi.dmw.cac.explore.model.Review>(this, "reviewRef", "reviewRef", org.slim3.datastore.ModelRef.class, it.polimi.dmw.cac.explore.model.Review.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Appreciation, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Appreciation, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final AppreciationMeta slim3_singleton = new AppreciationMeta();

    /**
     * @return the singleton
     */
    public static AppreciationMeta get() {
       return slim3_singleton;
    }

    /** */
    public AppreciationMeta() {
        super("Appreciation", it.polimi.dmw.cac.explore.model.Appreciation.class);
    }

    @Override
    public it.polimi.dmw.cac.explore.model.Appreciation entityToModel(com.google.appengine.api.datastore.Entity entity) {
        it.polimi.dmw.cac.explore.model.Appreciation model = new it.polimi.dmw.cac.explore.model.Appreciation();
        if (model.getAuthorRef() == null) {
            throw new NullPointerException("The property(authorRef) is null.");
        }
        model.getAuthorRef().setKey((com.google.appengine.api.datastore.Key) entity.getProperty("authorRef"));
        model.setKey(entity.getKey());
        if (model.getReviewRef() == null) {
            throw new NullPointerException("The property(reviewRef) is null.");
        }
        model.getReviewRef().setKey((com.google.appengine.api.datastore.Key) entity.getProperty("reviewRef"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        it.polimi.dmw.cac.explore.model.Appreciation m = (it.polimi.dmw.cac.explore.model.Appreciation) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        if (m.getAuthorRef() == null) {
            throw new NullPointerException("The property(authorRef) must not be null.");
        }
        entity.setProperty("authorRef", m.getAuthorRef().getKey());
        if (m.getReviewRef() == null) {
            throw new NullPointerException("The property(reviewRef) must not be null.");
        }
        entity.setProperty("reviewRef", m.getReviewRef().getKey());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        it.polimi.dmw.cac.explore.model.Appreciation m = (it.polimi.dmw.cac.explore.model.Appreciation) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        it.polimi.dmw.cac.explore.model.Appreciation m = (it.polimi.dmw.cac.explore.model.Appreciation) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        it.polimi.dmw.cac.explore.model.Appreciation m = (it.polimi.dmw.cac.explore.model.Appreciation) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
        it.polimi.dmw.cac.explore.model.Appreciation m = (it.polimi.dmw.cac.explore.model.Appreciation) model;
        if (m.getAuthorRef() == null) {
            throw new NullPointerException("The property(authorRef) must not be null.");
        }
        m.getAuthorRef().assignKeyIfNecessary(ds);
        if (m.getReviewRef() == null) {
            throw new NullPointerException("The property(reviewRef) must not be null.");
        }
        m.getReviewRef().assignKeyIfNecessary(ds);
    }

    @Override
    protected void incrementVersion(Object model) {
        it.polimi.dmw.cac.explore.model.Appreciation m = (it.polimi.dmw.cac.explore.model.Appreciation) model;
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
        it.polimi.dmw.cac.explore.model.Appreciation m = (it.polimi.dmw.cac.explore.model.Appreciation) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getAuthorRef() != null && m.getAuthorRef().getKey() != null){
            writer.setNextPropertyName("authorRef");
            encoder0.encode(writer, m.getAuthorRef(), maxDepth, currentDepth);
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getReviewRef() != null && m.getReviewRef().getKey() != null){
            writer.setNextPropertyName("reviewRef");
            encoder0.encode(writer, m.getReviewRef(), maxDepth, currentDepth);
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected it.polimi.dmw.cac.explore.model.Appreciation jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        it.polimi.dmw.cac.explore.model.Appreciation m = new it.polimi.dmw.cac.explore.model.Appreciation();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("authorRef");
        decoder0.decode(reader, m.getAuthorRef(), maxDepth, currentDepth);
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("reviewRef");
        decoder0.decode(reader, m.getReviewRef(), maxDepth, currentDepth);
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}
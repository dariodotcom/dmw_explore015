package it.polimi.dmw.cac.explore.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-12-23 13:27:04")
/** */
public final class ReviewMeta extends org.slim3.datastore.ModelMeta<it.polimi.dmw.cac.explore.model.Review> {

    /** */
    public final org.slim3.datastore.ModelRefAttributeMeta<it.polimi.dmw.cac.explore.model.Review, org.slim3.datastore.ModelRef<it.polimi.dmw.cac.explore.model.User>, it.polimi.dmw.cac.explore.model.User> authorRef = new org.slim3.datastore.ModelRefAttributeMeta<it.polimi.dmw.cac.explore.model.Review, org.slim3.datastore.ModelRef<it.polimi.dmw.cac.explore.model.User>, it.polimi.dmw.cac.explore.model.User>(this, "authorRef", "authorRef", org.slim3.datastore.ModelRef.class, it.polimi.dmw.cac.explore.model.User.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Review, java.lang.Long> dateRep = new org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Review, java.lang.Long>(this, "dateRep", "dateRep", long.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Review, java.lang.Integer> grade = new org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Review, java.lang.Integer>(this, "grade", "grade", int.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Review, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Review, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<it.polimi.dmw.cac.explore.model.Review> text = new org.slim3.datastore.StringAttributeMeta<it.polimi.dmw.cac.explore.model.Review>(this, "text", "text");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Review, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Review, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final ReviewMeta slim3_singleton = new ReviewMeta();

    /**
     * @return the singleton
     */
    public static ReviewMeta get() {
       return slim3_singleton;
    }

    /** */
    public ReviewMeta() {
        super("Review", it.polimi.dmw.cac.explore.model.Review.class);
    }

    @Override
    public it.polimi.dmw.cac.explore.model.Review entityToModel(com.google.appengine.api.datastore.Entity entity) {
        it.polimi.dmw.cac.explore.model.Review model = new it.polimi.dmw.cac.explore.model.Review();
        if (model.getAuthorRef() == null) {
            throw new NullPointerException("The property(authorRef) is null.");
        }
        model.getAuthorRef().setKey((com.google.appengine.api.datastore.Key) entity.getProperty("authorRef"));
        model.setDateRep(longToPrimitiveLong((java.lang.Long) entity.getProperty("dateRep")));
        model.setGrade(longToPrimitiveInt((java.lang.Long) entity.getProperty("grade")));
        model.setKey(entity.getKey());
        model.setText((java.lang.String) entity.getProperty("text"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        it.polimi.dmw.cac.explore.model.Review m = (it.polimi.dmw.cac.explore.model.Review) model;
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
        entity.setProperty("dateRep", m.getDateRep());
        entity.setProperty("grade", m.getGrade());
        entity.setProperty("text", m.getText());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        it.polimi.dmw.cac.explore.model.Review m = (it.polimi.dmw.cac.explore.model.Review) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        it.polimi.dmw.cac.explore.model.Review m = (it.polimi.dmw.cac.explore.model.Review) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        it.polimi.dmw.cac.explore.model.Review m = (it.polimi.dmw.cac.explore.model.Review) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
        it.polimi.dmw.cac.explore.model.Review m = (it.polimi.dmw.cac.explore.model.Review) model;
        if (m.getAuthorRef() == null) {
            throw new NullPointerException("The property(authorRef) must not be null.");
        }
        m.getAuthorRef().assignKeyIfNecessary(ds);
    }

    @Override
    protected void incrementVersion(Object model) {
        it.polimi.dmw.cac.explore.model.Review m = (it.polimi.dmw.cac.explore.model.Review) model;
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
        it.polimi.dmw.cac.explore.model.Review m = (it.polimi.dmw.cac.explore.model.Review) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getAuthorRef() != null && m.getAuthorRef().getKey() != null){
            writer.setNextPropertyName("authorRef");
            encoder0.encode(writer, m.getAuthorRef(), maxDepth, currentDepth);
        }
        writer.setNextPropertyName("dateRep");
        encoder0.encode(writer, m.getDateRep());
        writer.setNextPropertyName("grade");
        encoder0.encode(writer, m.getGrade());
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getText() != null){
            writer.setNextPropertyName("text");
            encoder0.encode(writer, m.getText());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected it.polimi.dmw.cac.explore.model.Review jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        it.polimi.dmw.cac.explore.model.Review m = new it.polimi.dmw.cac.explore.model.Review();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("authorRef");
        decoder0.decode(reader, m.getAuthorRef(), maxDepth, currentDepth);
        reader = rootReader.newObjectReader("dateRep");
        m.setDateRep(decoder0.decode(reader, m.getDateRep()));
        reader = rootReader.newObjectReader("grade");
        m.setGrade(decoder0.decode(reader, m.getGrade()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("text");
        m.setText(decoder0.decode(reader, m.getText()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}
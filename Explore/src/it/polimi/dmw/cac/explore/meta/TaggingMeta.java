package it.polimi.dmw.cac.explore.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-12-23 13:27:04")
/** */
public final class TaggingMeta extends org.slim3.datastore.ModelMeta<it.polimi.dmw.cac.explore.model.Tagging> {

    /** */
    public final org.slim3.datastore.ModelRefAttributeMeta<it.polimi.dmw.cac.explore.model.Tagging, org.slim3.datastore.ModelRef<it.polimi.dmw.cac.explore.model.Exhibition>, it.polimi.dmw.cac.explore.model.Exhibition> exhibitionRef = new org.slim3.datastore.ModelRefAttributeMeta<it.polimi.dmw.cac.explore.model.Tagging, org.slim3.datastore.ModelRef<it.polimi.dmw.cac.explore.model.Exhibition>, it.polimi.dmw.cac.explore.model.Exhibition>(this, "exhibitionRef", "exhibitionRef", org.slim3.datastore.ModelRef.class, it.polimi.dmw.cac.explore.model.Exhibition.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Tagging, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Tagging, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.ModelRefAttributeMeta<it.polimi.dmw.cac.explore.model.Tagging, org.slim3.datastore.ModelRef<it.polimi.dmw.cac.explore.model.Tag>, it.polimi.dmw.cac.explore.model.Tag> tagRef = new org.slim3.datastore.ModelRefAttributeMeta<it.polimi.dmw.cac.explore.model.Tagging, org.slim3.datastore.ModelRef<it.polimi.dmw.cac.explore.model.Tag>, it.polimi.dmw.cac.explore.model.Tag>(this, "tagRef", "tagRef", org.slim3.datastore.ModelRef.class, it.polimi.dmw.cac.explore.model.Tag.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Tagging, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Tagging, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Tagging, java.lang.Double> weight = new org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Tagging, java.lang.Double>(this, "weight", "weight", double.class);

    private static final TaggingMeta slim3_singleton = new TaggingMeta();

    /**
     * @return the singleton
     */
    public static TaggingMeta get() {
       return slim3_singleton;
    }

    /** */
    public TaggingMeta() {
        super("Tagging", it.polimi.dmw.cac.explore.model.Tagging.class);
    }

    @Override
    public it.polimi.dmw.cac.explore.model.Tagging entityToModel(com.google.appengine.api.datastore.Entity entity) {
        it.polimi.dmw.cac.explore.model.Tagging model = new it.polimi.dmw.cac.explore.model.Tagging();
        if (model.getExhibitionRef() == null) {
            throw new NullPointerException("The property(exhibitionRef) is null.");
        }
        model.getExhibitionRef().setKey((com.google.appengine.api.datastore.Key) entity.getProperty("exhibitionRef"));
        model.setKey(entity.getKey());
        if (model.getTagRef() == null) {
            throw new NullPointerException("The property(tagRef) is null.");
        }
        model.getTagRef().setKey((com.google.appengine.api.datastore.Key) entity.getProperty("tagRef"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        model.setWeight(doubleToPrimitiveDouble((java.lang.Double) entity.getProperty("weight")));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        it.polimi.dmw.cac.explore.model.Tagging m = (it.polimi.dmw.cac.explore.model.Tagging) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        if (m.getExhibitionRef() == null) {
            throw new NullPointerException("The property(exhibitionRef) must not be null.");
        }
        entity.setProperty("exhibitionRef", m.getExhibitionRef().getKey());
        if (m.getTagRef() == null) {
            throw new NullPointerException("The property(tagRef) must not be null.");
        }
        entity.setProperty("tagRef", m.getTagRef().getKey());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("weight", m.getWeight());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        it.polimi.dmw.cac.explore.model.Tagging m = (it.polimi.dmw.cac.explore.model.Tagging) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        it.polimi.dmw.cac.explore.model.Tagging m = (it.polimi.dmw.cac.explore.model.Tagging) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        it.polimi.dmw.cac.explore.model.Tagging m = (it.polimi.dmw.cac.explore.model.Tagging) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
        it.polimi.dmw.cac.explore.model.Tagging m = (it.polimi.dmw.cac.explore.model.Tagging) model;
        if (m.getExhibitionRef() == null) {
            throw new NullPointerException("The property(exhibitionRef) must not be null.");
        }
        m.getExhibitionRef().assignKeyIfNecessary(ds);
        if (m.getTagRef() == null) {
            throw new NullPointerException("The property(tagRef) must not be null.");
        }
        m.getTagRef().assignKeyIfNecessary(ds);
    }

    @Override
    protected void incrementVersion(Object model) {
        it.polimi.dmw.cac.explore.model.Tagging m = (it.polimi.dmw.cac.explore.model.Tagging) model;
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
        it.polimi.dmw.cac.explore.model.Tagging m = (it.polimi.dmw.cac.explore.model.Tagging) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getExhibitionRef() != null && m.getExhibitionRef().getKey() != null){
            writer.setNextPropertyName("exhibitionRef");
            encoder0.encode(writer, m.getExhibitionRef(), maxDepth, currentDepth);
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getTagRef() != null && m.getTagRef().getKey() != null){
            writer.setNextPropertyName("tagRef");
            encoder0.encode(writer, m.getTagRef(), maxDepth, currentDepth);
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.setNextPropertyName("weight");
        encoder0.encode(writer, m.getWeight());
        writer.endObject();
    }

    @Override
    protected it.polimi.dmw.cac.explore.model.Tagging jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        it.polimi.dmw.cac.explore.model.Tagging m = new it.polimi.dmw.cac.explore.model.Tagging();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("exhibitionRef");
        decoder0.decode(reader, m.getExhibitionRef(), maxDepth, currentDepth);
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("tagRef");
        decoder0.decode(reader, m.getTagRef(), maxDepth, currentDepth);
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        reader = rootReader.newObjectReader("weight");
        m.setWeight(decoder0.decode(reader, m.getWeight()));
        return m;
    }
}
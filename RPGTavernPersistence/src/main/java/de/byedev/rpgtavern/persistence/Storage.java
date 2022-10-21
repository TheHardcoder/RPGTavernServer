package de.byedev.rpgtavern.persistence;

import de.byedev.rpgtavern.persistence.entities.RPGRoot;
import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.embedded.types.EmbeddedStorageFoundation;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.ServiceScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

@Component(service=Storage.class, scope = ServiceScope.SINGLETON)
public class Storage {

    public static final Logger LOG = LoggerFactory.getLogger(Storage.class);

    private static final String PERSISTENCE_PATH = System.getProperty("karaf.home") + "/data/storage";
    private EmbeddedStorageManager store;

    private RPGRoot root = new RPGRoot();

    public Storage() {
        LOG.info(PERSISTENCE_PATH);
        EmbeddedStorageFoundation<?> foundation = EmbeddedStorage.Foundation(Path.of(PERSISTENCE_PATH));
        store = foundation.createEmbeddedStorageManager(root).start();
    }

    @Deactivate
    public void stopStorage() {
        store.shutdown();
    }

    public EmbeddedStorageManager getStore() {
        return store;
    }

    public RPGRoot getRoot() {
        return root;
    }

    public void storeRoot()
    {
        store.storeRoot();
    }

    public void store(Object obj)
    {
        LOG.info("Storing " + obj.getClass() + ": " + store.store(obj));
    }
}

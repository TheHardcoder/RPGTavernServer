package de.byedev.rpgtavern.persistence.entities;

import one.microstream.X;
import one.microstream.persistence.binary.types.Binary;
import one.microstream.persistence.binary.types.BinaryLegacyTypeHandler;
import one.microstream.persistence.types.PersistenceLoadHandler;
import one.microstream.persistence.types.PersistenceReferenceLoader;

public class TokenLegacyTypeHandler extends BinaryLegacyTypeHandler.AbstractCustom<Token>
{
    //need to know the binary layout of the persisted legacy class
    private static final long
            BINARY_OFFSET_name = 0,
            BINARY_OFFSET_directions = BINARY_OFFSET_name + Binary.objectIdByteLength();


    public TokenLegacyTypeHandler()
    {
        //introduce the field names of the legacy class
        super(Token.class,
                X.List());
    }

    @Override
    public boolean hasPersistedReferences()
    {
        // persisted data records have references to other persisted data records
        return true;
    }

    @Override
    public boolean hasVaryingPersistedLengthInstances()
    {
        /*the same instance can never have a varying persisted length, since the NicePlace class
         *only has to references as member.
         *
         * Collections are an example for variable length instances.
         * The same collection instance can contain 2 elements at one store and 3 at another store.
         */

        return true;
    }


    @Override
    public Token create(
            final Binary bytes,
            final PersistenceLoadHandler loadHandler
    )
    {
        //required instances may not be available, yet, at creation time. Thus create dummy and fill in #update.
        return new Token();
    }

    @Override
    public void updateState(
            final Binary bytes,
            final Token instance,
            final PersistenceLoadHandler handler
    )
    {
    }

    @Override
    public void iterateLoadableReferences(
            final Binary bytes,
            final PersistenceReferenceLoader iterator
    )
    {

    }
}
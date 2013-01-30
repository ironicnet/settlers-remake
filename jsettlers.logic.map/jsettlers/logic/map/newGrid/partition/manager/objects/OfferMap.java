package jsettlers.logic.map.newGrid.partition.manager.objects;

import jsettlers.common.material.EMaterialType;
import jsettlers.common.position.ShortPoint2D;
import jsettlers.logic.algorithms.queue.ITypeAcceptor;
import jsettlers.logic.map.newGrid.partition.manager.datastructures.PositionableHashMap;

/**
 * The map of offers in the current partition. Keeps track of all offers.
 * <p>
 * It is also an acceptor that accepts all materials for which we have offers.
 * 
 */
public class OfferMap extends PositionableHashMap<MaterialOffer> implements ITypeAcceptor<EMaterialType> {
	private static final long serialVersionUID = 194211819683736498L;

	private int[] count = new int[EMaterialType.NUMBER_OF_MATERIALS];

	@Override
	public void set(ShortPoint2D position, MaterialOffer object) {
		removeObjectAt(position);
		count[object.materialType.ordinal()]++;
		super.set(position, object);
	}

	@Override
	public MaterialOffer removeObjectAt(ShortPoint2D position) {
		MaterialOffer removed = super.removeObjectAt(position);
		if (removed != null) {
			count[removed.materialType.ordinal()]--;
		}
		return removed;
	}

	@Override
	public boolean accepts(EMaterialType type) {
		return count[type.ordinal()] > 0;
	}
}

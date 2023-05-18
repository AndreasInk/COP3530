package ood;

// Demonstrating multiple inhertiance for interfaces
public interface Insurable extends SuperStoreSellableItem, Transportable {
	 double getInsuredValue();
}

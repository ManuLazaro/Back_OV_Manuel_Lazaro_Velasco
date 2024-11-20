package main.OV.util;


import main.OV.db.entity.CenterEntity;

import java.util.Comparator;

public class CenterAddressComparator implements Comparator<CenterEntity> {
    @Override
    public int compare(CenterEntity o1, CenterEntity o2) {
        return 0;
    }

    /*
    public int compare(BuildingEntity b1, BuildingEntity b2) {
        AddressGeneralEntity addressGeneralB1 = b1.getAddressBuildings().iterator().next();
        AddressDetailedEntity addressDetailedB1 = b1.getAddressDetailed();

        String streetCompleteB1 = addressGeneralB1.getType().getName() + " " +
                addressGeneralB1.getStreet().getName();
        streetCompleteB1 += addressGeneralB1.getNumber() != null ? " " + addressGeneralB1.getNumber() : "";
        streetCompleteB1 += addressDetailedB1.getBlock() != null && !addressDetailedB1.getBlock().isEmpty() ?
                " " + addressDetailedB1.getBlock() : "";
        streetCompleteB1 += addressDetailedB1.getStaircase() != null && !addressDetailedB1.getStaircase().isEmpty() ?
                " " + addressDetailedB1.getStaircase() : "";
        streetCompleteB1 += addressDetailedB1.getFloor() != null && !addressDetailedB1.getFloor().isEmpty() ?
                " " + addressDetailedB1.getFloor() : "";
        streetCompleteB1 += addressDetailedB1.getDoor() != null && !addressDetailedB1.getDoor().isEmpty() ?
                " " + addressDetailedB1.getDoor() : "";

        AddressGeneralEntity addressGeneralB2 = b2.getAddressBuildings().iterator().next();
        AddressDetailedEntity addressDetailedB2 = b2.getAddressDetailed();

        String streetCompleteB2 = addressGeneralB2.getType().getName() + " " +
                addressGeneralB2.getStreet().getName();
        streetCompleteB2 += addressGeneralB2.getNumber() != null ? " " + addressGeneralB2.getNumber() : "";
        streetCompleteB2 += addressDetailedB2.getBlock() != null && !addressDetailedB2.getBlock().isEmpty() ?
                " " + addressDetailedB2.getBlock() : "";
        streetCompleteB2 += addressDetailedB2.getStaircase() != null && !addressDetailedB2.getStaircase().isEmpty() ?
                " " + addressDetailedB2.getStaircase() : "";
        streetCompleteB2 += addressDetailedB2.getFloor() != null && !addressDetailedB2.getFloor().isEmpty() ?
                " " + addressDetailedB2.getFloor() : "";
        streetCompleteB2 += addressDetailedB2.getDoor() != null && !addressDetailedB2.getDoor().isEmpty() ?
                " " + addressDetailedB2.getDoor() : "";

        return streetCompleteB1.compareTo(streetCompleteB2);
    }

    @Override
    public int compare(CenterEntity o1, CenterEntity o2) {
        return 0;
    }*/
}

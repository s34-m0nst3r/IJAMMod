package com.idtech.entity.projectiles;

import net.minecraft.core.BlockPos;
import java.util.ArrayList;
import java.util.List;

public class PortalManager {

    private static final List<BlockPos> portals = new ArrayList<>();

    public static void addPortal(BlockPos pos) {
        if (portals.size() >= 2) {
            portals.remove(0); // Remove the oldest portal if we already have 2
        }
        portals.add(pos);
    }

    public static BlockPos getOtherPortal(BlockPos currentPortal) {
        if (portals.size() < 2) {
            return null; // Not enough portals for teleportation
        }
        for (BlockPos pos : portals) {
            if (!pos.equals(currentPortal)) {
                return pos;
            }
        }
        return null;
    }

    public static List<BlockPos> getPortals() {
        return portals;
    }

    public static void clearPortals() {
        portals.clear();
    }
}
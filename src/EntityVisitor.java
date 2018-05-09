public interface EntityVisitor<R> {
    R visit(Ore ore);
    R visit(MinerFull minerFull);
    R visit(MinerNotFull minerNotFull);
    R visit(Obstacle obstacle);
    R visit(Vein vein);
    R visit(Blacksmith blacksmith);
    R visit(Quake quake);
    R visit(OreBlob oreBlob);
    R visit(Alon alon);





}

public class AnimatedActorVisitor extends AllFalseEntityVisitor implements EntityVisitor<Boolean> {
    public Boolean visit(Ore ore){
        return true;
    }
    public Boolean visit(Vein vein){
        return true;
    }
    public Boolean visit(Quake quake){
        return true;
    }
    public Boolean visit(OreBlob oreBlob){
        return true;
    }
    public Boolean visit(MinerNotFull minerNotFull){
        return true;
    }
    public Boolean visit(MinerFull minerFull){
        return true;
    }
    public Boolean visit(Alon alon){
        return true;
    }

}


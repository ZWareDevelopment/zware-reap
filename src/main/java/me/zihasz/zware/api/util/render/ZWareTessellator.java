package me.zihasz.zware.api.util.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.math.BlockPos;

public class ZWareTessellator extends Tessellator {

    public static ZWareTessellator INSTANCE = new ZWareTessellator();

    public ZWareTessellator() {
        super(0x200000);
    }

    public void drawBlock(BlockPos blockIn, int argb) {

    }
    public void drawBlockOutline(BlockPos blockIn, int argb, int lineWidth) {

    }
    public void drawBlockFull(BlockPos blockIn, int argb, int lineWidth) {
        this.drawBlockOutline(blockIn, argb, lineWidth);
        this.drawBlock(blockIn, argb);
    }

    public void drawFlat(BlockPos blockIn, int argb) {

    }
    public void drawFlatOutline(BlockPos blockIn, int argb, int lineWidth) {

    }
    public void drawFlatFull(BlockPos blockIn, int argb, int lineWidth) {
        this.drawFlatOutline(blockIn, argb, lineWidth);
        this.drawFlat(blockIn, argb);
    }

    public void drawSlab(BlockPos blockIn, int argb, float heightIn) {

    }
    public void drawSlabOutline(BlockPos blockIn, int argb, int lineWidth, float heightIn) {

    }
    public void drawSlabFull(BlockPos blockIn, int argb, int lineWidth, float heightIn) {
        float height = heightIn >= 0 ? 1 : heightIn;
        this.drawSlabOutline(blockIn, argb, lineWidth, height);
        this.drawSlab(blockIn, argb, height);
    }

}

package me.zihasz.zware.impl.module.combat;

import jdk.nashorn.internal.ir.Block;
import me.zihasz.zware.ZWare;
import me.zihasz.zware.api.module.Module;
import me.zihasz.zware.api.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.awt.*;

public class AutoCrystal extends Module {

    // Base
    Setting<Boolean> sPlace = register(new Setting<Boolean>("Place", "Place crystals", true));
    Setting<Boolean> sBreak = register(new Setting<Boolean>("Break", "Break crystals", true));
    Setting<CAPlaceMode> sPlaceMode = register(new Setting<CAPlaceMode>("PlaceMode", "Rules for placing", CAPlaceMode.ALWAYS));
    Setting<CABreakMode> sBreakMode = register(new Setting<CABreakMode>("BreakMode", "Rules for breaking", CABreakMode.SMART));

    // Ranges
    Setting<Float> sPlaceRange = register(new Setting<Float>("PlaceRange", "Range to place at", 5.0f, 1.0f, 6.0f));
    Setting<Float> sBreakRange = register(new Setting<Float>("BreakRange", "Range to break at", 5.0f, 1.0f, 6.0f));
    Setting<Float> sWallsRange = register(new Setting<Float>("WallsRange", "Range through walls", 5.0f, 1.0f, 6.0f));

    // Delays
    Setting<Float> sPlaceDelay = register(new Setting<Float>("PlaceDelay", "Delay for placing", 5.0f, 1.0f, 6.0f));
    Setting<Float> sBreakDelay = register(new Setting<Float>("BreakDelay", "Delay for breaking", 5.0f, 1.0f, 6.0f));

    // Damage
    Setting<Float> sMinHealth = register(new Setting<Float>("MinHealth", "Minimum health for CA to be enabled", 6f, 1f, 36f));
    Setting<Float> sMinPlaceDamage = register(new Setting<Float>("MinPlaceDamage", "Minimum health for CA to place", 6f, 1f, 36f));
    Setting<Float> sMinBreakDamage = register(new Setting<Float>("MinBreakDamage", "Minimum health for CA to break", 6f, 1f, 36f));
    Setting<Float> sMaxSelfDamage = register(new Setting<Float>("MaxSelfDamage", "Maximum damage that your crystals do to you", 10f, 1f, 20f));
    Setting<Float> sFacePlaceHealth = register(new Setting<Float>("FacePlaceHP", "Enemy health to start face placing", 6f, 1f, 20f));

    // MultiPlace (MP)
    Setting<Boolean> sMultiPlace = register(new Setting<Boolean>("MultiPlace", "Place more crystals", false));
    Setting<Boolean> sDynamicMultiPlace = register(new Setting<Boolean>("DynamicMP", "Dynamically toggle MultiPlace", false));
    Setting<Integer> sMultiPlaceIterations = register(new Setting<Integer>("MPIterations", "How many more crystals?", 2, 1, 10));
    Setting<Integer> sMultiPlaceHealth = register(new Setting<Integer>("DMPDamage", "What health should we toggle MultiPlace", 8, 1, 15));

    // Packet
    Setting<Boolean> sPacketPlace = register(new Setting<Boolean>("PacketPlace", "Break with packets only", false));
    Setting<Boolean> sPacketBreak = register(new Setting<Boolean>("PacketBreak", "Place with packets only", false));

    // Rotations
    Setting<Boolean> sRotate = register(new Setting<Boolean>("Rotate", "duh", true));
    Setting<Boolean> sSpoofRotations = register(new Setting<Boolean>("SpoofRotations", "spoof duh", true));
    Setting<CARotationMode> sRotationMode = register(new Setting<CARotationMode>("RotationMode", "How rotate", CARotationMode.SERVER));

    // Render
    Setting<Boolean> sRender = register(new Setting<Boolean>("Render", "Render the block that the crystal is placed on", true));
    Setting<Boolean> sRenderSimulated = register(new Setting<Boolean>("RenderSimulated", "Render the block that the simulated crystal is placed on", true));
    Setting<Color> sRenderColor = register(new Setting<Color>("RenderColor", "What color should the rendering be", new Color(255, 255, 255, 200)));
    Setting<Color> sRenderColorSimulated = register(new Setting<Color>("RenderColorSimulated", "What color the rendering of the simulation should be", new Color(255, 255, 255, 200)));
    Setting<CARenderMode> sRenderMode = register(new Setting<CARenderMode>("RenderMode", "RenderMode for CA rendering", CARenderMode.FULL));
    Setting<Boolean> sRenderOutline = register(new Setting<Boolean>("RenderOutline", "Should we render an outline", true));
    Setting<Boolean> sRenderDamage = register(new Setting<Boolean>("RenderDamage", "Render the damage of the crystals", true));
    Setting<Boolean> sRenderRange = register(new Setting<Boolean>("RenderRange", "Render the range of the crystals from the target", true));

    // Calibration
    Setting<Boolean> sCalibrationMode = register(new Setting<Boolean>("CalibrationMode", "Helps config/calibrate the CA", false));
    Setting<Boolean> sRangeCalibration = register(new Setting<Boolean>("RangeCalibration", "Helps calibrate the range", false));
    Setting<Boolean> sHandCalibration = register(new Setting<Boolean>("HandCalibration", "Helps calibrate the hand", false));
    Setting<Boolean> sDelayCalibration = register(new Setting<Boolean>("DelayCalibration", "Helps calibrate the delay", false));
    Setting<CADelayCalibrationMode> sDelayCalibrationMode = register(new Setting<CADelayCalibrationMode>("DelayCalibrationMode", "What should we use to calibrate delay", CADelayCalibrationMode.PING));

    // Simulation
    Setting<Boolean> sSimulatePlace = register(new Setting<Boolean>("SimulateBreak", "Simulate Breaking of crystals", false));
    Setting<Boolean> sSimulateBreak = register(new Setting<Boolean>("SimulatePlace", "Simulate Placing of crystals", false));
    Setting<Boolean> sSimulatePing = register(new Setting<Boolean>("SimulatePing", "Simulates ping spikes/changes", false));

    // Misc
    Setting<Boolean> sPingAdapt = register(new Setting<Boolean>("PingAdapt", "", true));
    Setting<Boolean> sSwitch = register(new Setting<Boolean>("Switch", "Switch to crystals in hotbar", false));
    Setting<Boolean> sSwing = register(new Setting<Boolean>("Swing", "Should we swing hands", true));
    Setting<EnumHand> sHand = register(new Setting<EnumHand>("Hand", "What hand should we \"place\" with", EnumHand.OFF_HAND));
    Setting<Boolean> sPredict = register(new Setting<Boolean>("Predict", "Predict the moves of the target", false));
    Setting<Boolean> sPredictCrystals = register(new Setting<Boolean>("PredictCrystals", "Predict where the enemy would place crystals", false));
    Setting<Boolean> sAntiWeakness = register(new Setting<Boolean>("AntiWeakness", "If wao moment it uses sword to break", true));
    Setting<Boolean> sSilentAntiWeakness = register(new Setting<Boolean>("SilentAW", "Packet mode for AntiWeakness", true));
    Setting<Boolean> sAntiDeSync = register(new Setting<Boolean>("AntiDeSync", "Prevents bad things", true));
    Setting<Boolean> sAntiStickyBreak = register(new Setting<Boolean>("A    ntiStickyBreak", "Prevents CA getting stuck", true));
    Setting<Integer> sABSAttempts = register(new Setting<Integer>("ABSAttempts", "How many attempts should we take at breaking a crystal", 7, 1, 20));
    Setting<CAThreadMode> sThreadMode = register(new Setting<CAThreadMode>("", "", CAThreadMode.NONE));

    EntityLivingBase placeTarget;
    EntityEnderCrystal breakTarget;

    BlockPos renderPos;
    BlockPos simulateRenderPos;

    public AutoCrystal() {
        super("AutoCrystal", "Kills block game people with boom boom", Category.COMBAT);
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onUpdate() {

    }

    private boolean doCrystalAura() {
        targeting();
        return false;
    }
    public void targeting() {
        placeTarget = ((TargetModule)ZWare.moduleManager.getModuleByName("Target")).getTarget();
    }

    private void placeC() {
        BlockPos pos = calculatePlacePos(placeTarget);
        conn.sendPacket(new CPacketPlayerTryUseItemOnBlock(
                pos,
                calculateFacing(pos),
                sHand.getValue(),
                (float) calculateRotation(pos).x,
                (float) calculateRotation(pos).y,
                (float) calculateRotation(pos).z
        ));

        if (sSwing.getValue()) swingHand();
    }

    private void breakC() {}

    private float calculateDamage(Entity entity, EntityEnderCrystal crystal)    {
        return 0f;
    }
    private BlockPos calculatePlacePos(Entity target) {
        return null;
    }
    private EnumFacing calculateFacing(BlockPos pos) {
        return null;
    }
    private Vec3d calculateRotation(BlockPos pos) {
        return new Vec3d(0f, 0f, 0f);
    }

    private void swingHand() {
        conn.sendPacket(new CPacketAnimation(sHand.getValue()));
    }

    private float simulatePlace(Entity target) {
        return 0f;
    }
    private float simulateBreak(EntityEnderCrystal crystal) {
        return 0f;
    }
    private float simulateFull(Entity target, BlockPos crystalPos) {
        return 0f;
    }

    private Vec3d predictMovement(Entity target) {
        return null;
    }
    private BlockPos predictCrystals(Entity target) {
        return null;
    }

    private void renderCrystalEntity(EntityEnderCrystal crystal) {

    }
    private void renderCrystalBlock(Block crystal) {

    }
    private void renderTarget(EntityLivingBase target) {

    }
    private void renderPredict(BlockPos predictedCrystal) {

    }

    private void calibrateRange() {

    }
    private void calibrateDelay() {

    }
    private void calibrateHand() {

    }

    enum CAPlaceMode {
        ALWAYS,
        LETHAL
    }
    enum CABreakMode {
        ALL,
        OWN,
        SMART
    }
    enum CARenderMode {
        FULL,
        HALF,
        FLAT
    }
    enum CADelayCalibrationMode {
        PING,
        TPS
    }
    enum CARotationMode {
        NONE,
        CLIENT,
        SERVER
    }
    enum CAThreadMode {
        NONE,
        /* SINGLE, */
        /* MULTI */
    }
}

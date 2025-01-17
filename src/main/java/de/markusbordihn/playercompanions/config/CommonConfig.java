/**
 * Copyright 2021 Markus Bordihn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.markusbordihn.playercompanions.config;

import org.apache.commons.lang3.tuple.Pair;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import de.markusbordihn.playercompanions.Constants;
import de.markusbordihn.playercompanions.client.gui.GuiPosition;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class CommonConfig {

  protected static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public static final ForgeConfigSpec commonSpec;
  public static final Config COMMON;

  public static final String MIN_GROUP_SIZE_TEXT = "Min group size.";
  public static final String MAX_GROUP_SIZE_TEXT = "Max group size.";
  public static final String SPAWN_WEIGHT_TEXT = "Spawn weight.";

  protected CommonConfig() {}

  static {
    com.electronwill.nightconfig.core.Config.setInsertionOrderPreserved(true);
    final Pair<Config, ForgeConfigSpec> specPair =
        new ForgeConfigSpec.Builder().configure(Config::new);
    commonSpec = specPair.getRight();
    COMMON = specPair.getLeft();
    log.info("{} Common config ...", Constants.LOG_REGISTER_PREFIX);
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, commonSpec);
  }

  public static class Config {

    public final ForgeConfigSpec.BooleanValue respawnOnDeath;
    public final ForgeConfigSpec.IntValue respawnDelay;
    public final ForgeConfigSpec.BooleanValue enableCompanionGhost;
    public final ForgeConfigSpec.BooleanValue friendlyFire;
    public final ForgeConfigSpec.IntValue maxHealth;

    public final ForgeConfigSpec.EnumValue<GuiPosition> guiPosition;
    public final ForgeConfigSpec.IntValue guiOffsetX;
    public final ForgeConfigSpec.IntValue guiOffsetY;

    public final ForgeConfigSpec.BooleanValue hudEnabled;
    public final ForgeConfigSpec.BooleanValue hudNameTagEnabled;
    public final ForgeConfigSpec.BooleanValue hudOwnerEnabled;
    public final ForgeConfigSpec.BooleanValue hudStatusEnabled;
    public final ForgeConfigSpec.IntValue hudDisplayRadius;

    public final ForgeConfigSpec.IntValue collectorTypeRadius;

    public final ForgeConfigSpec.IntValue healerTypeRadius;
    public final ForgeConfigSpec.IntValue healerTypeMinAmount;
    public final ForgeConfigSpec.IntValue healerTypeMaxAmount;

    public final ForgeConfigSpec.IntValue supporterTypeRadius;
    public final ForgeConfigSpec.IntValue supporterTypeDamageBoostDuration;
    public final ForgeConfigSpec.IntValue supporterTypeDamageResistanceDuration;
    public final ForgeConfigSpec.IntValue supporterTypeFireResistanceDuration;

    public final ForgeConfigSpec.BooleanValue fairySpawnEnable;
    public final ForgeConfigSpec.IntValue fairyMinGroup;
    public final ForgeConfigSpec.IntValue fairyMaxGroup;
    public final ForgeConfigSpec.IntValue fairyWeight;

    public final ForgeConfigSpec.BooleanValue pigSpawnEnable;
    public final ForgeConfigSpec.IntValue pigMinGroup;
    public final ForgeConfigSpec.IntValue pigMaxGroup;
    public final ForgeConfigSpec.IntValue pigWeight;

    public final ForgeConfigSpec.BooleanValue roosterSpawnEnable;
    public final ForgeConfigSpec.IntValue roosterMinGroup;
    public final ForgeConfigSpec.IntValue roosterMaxGroup;
    public final ForgeConfigSpec.IntValue roosterWeight;

    public final ForgeConfigSpec.BooleanValue smallGhastSpawnEnable;
    public final ForgeConfigSpec.IntValue smallGhastMinGroup;
    public final ForgeConfigSpec.IntValue smallGhastMaxGroup;
    public final ForgeConfigSpec.IntValue smallGhastWeight;
    public final ForgeConfigSpec.IntValue smallGhastExplosionPower;

    public final ForgeConfigSpec.BooleanValue smallSlimeSpawnEnable;
    public final ForgeConfigSpec.IntValue smallSlimeMinGroup;
    public final ForgeConfigSpec.IntValue smallSlimeMaxGroup;
    public final ForgeConfigSpec.IntValue smallSlimeWeight;

    public final ForgeConfigSpec.BooleanValue snailSpawnEnable;
    public final ForgeConfigSpec.IntValue snailMinGroup;
    public final ForgeConfigSpec.IntValue snailMaxGroup;
    public final ForgeConfigSpec.IntValue snailWeight;

    public final ForgeConfigSpec.BooleanValue welshCorgiSpawnEnable;
    public final ForgeConfigSpec.IntValue welshCorgiMinGroup;
    public final ForgeConfigSpec.IntValue welshCorgiMaxGroup;
    public final ForgeConfigSpec.IntValue welshCorgiWeight;

    Config(ForgeConfigSpec.Builder builder) {
      builder.comment("Player Companion's (General configuration)");

      builder.push("General");
      respawnOnDeath =
          builder.comment("Respawn companion on death.").define("respawnOnDeath", true);
      respawnDelay = builder.comment("Respawn delay in seconds (1200 secs = in-game day).")
          .defineInRange("respawnDelay", 1200, 1, 8400);
      friendlyFire = builder
          .comment("Allow's damage to an owned companion by the owner or their owned companions.")
          .define("friendlyFire", false);
      enableCompanionGhost = builder.comment("Enable / Disable ghosts for died player companions.")
          .define("enableCompanionGhost", true);
      maxHealth = builder.comment("The max health a companion can get with level 60.")
          .defineInRange("maxHealth", 20, 0, 200);
      builder.pop();

      builder.push("Gui");
      guiPosition = builder.comment("Position for the gui elements.").defineEnum("guiPosition",
          GuiPosition.HOTBAR_RIGHT);
      guiOffsetX = builder.comment("The offset on X axis from chosen position.")
          .defineInRange("guiOffsetX", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
      guiOffsetY = builder.comment("The offset on X axis from chosen position.")
          .defineInRange("guiOffsetY", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
      builder.pop();

      builder.push("Hud");
      hudEnabled = builder.comment("Enable hud.").define("hudEnabled", false);
      hudNameTagEnabled = builder.comment("Enable additional display of name tag.")
          .define("hudNameTagEnabled", false);
      hudOwnerEnabled = builder.comment("Enable display of owner.").define("hudOwnerEnabled", true);
      hudStatusEnabled = builder.comment("Enable display of status like sitting.")
          .define("hudStatusEnabled", true);
      hudDisplayRadius = builder.comment("Radius in which hud is displayed.")
          .defineInRange("hudDisplayRadius", 32, 0, 256);
      builder.pop();

      builder.push("Collector Type");
      collectorTypeRadius =
          builder.comment("Defines the radius in which items are automatically collected.")
              .defineInRange("collectorTypeRadius", 3, 0, 16);
      builder.pop();

      builder.push("Healer Type");
      healerTypeRadius = builder.comment("Defines the radius in which players are healed.")
          .defineInRange("healerTypeRadius", 8, 0, 32);
      healerTypeMinAmount =
          builder.comment("Defines the min. healing amount depending on the level.")
              .defineInRange("healerTypeMinAmount", 2, 0, 16);
      healerTypeMaxAmount =
          builder.comment("Defines the max. healing amount depending on the level.")
              .defineInRange("healerTypeMaxAmount", 10, 0, 16);
      builder.pop();

      builder.push("Supporter Type");
      supporterTypeRadius = builder.comment("Defines the radius in which players are buffed.")
          .defineInRange("supporterTypeRadius", 8, 0, 32);
      supporterTypeDamageBoostDuration =
          builder.comment("Defines the amount of ticks how long the damage boost is enabled.")
              .defineInRange("supporterTypeDamageBoostDuration", 1200, 20, 6000);
      supporterTypeDamageResistanceDuration = builder
          .comment(
              "Defines the amount of ticks how long the damage resistance protection is enabled.")
          .defineInRange("supporterTypeDamageResistanceDuration", 1200, 20, 6000);
      supporterTypeFireResistanceDuration = builder
          .comment(
              "Defines the amount of ticks how long the fire resistance protection is enabled.")
          .defineInRange("supporterTypeFireResistanceDuration", 1200, 20, 6000);
      builder.pop();

      builder.push("Fairy");
      fairySpawnEnable =
          builder.comment("Enable/Disable the fairy spawn.").define("fairySpawnEnable", true);
      fairyMinGroup = builder.comment(MIN_GROUP_SIZE_TEXT).defineInRange("fairyMinGroup", 1, 0, 64);
      fairyMaxGroup = builder.comment(MAX_GROUP_SIZE_TEXT).defineInRange("fairyMaxGroup", 2, 0, 64);
      fairyWeight = builder.comment(SPAWN_WEIGHT_TEXT).defineInRange("fairyWeight", 6, 0, 100);
      builder.pop();

      builder.push("Pig");
      pigSpawnEnable =
          builder.comment("Enable/Disable the pig spawn.").define("pigSpawnEnable", true);
      pigMinGroup = builder.comment(MIN_GROUP_SIZE_TEXT).defineInRange("pigMinGroup", 1, 0, 64);
      pigMaxGroup = builder.comment(MAX_GROUP_SIZE_TEXT).defineInRange("pigMaxGroup", 1, 0, 64);
      pigWeight = builder.comment(SPAWN_WEIGHT_TEXT).defineInRange("pigWeight", 6, 0, 100);
      builder.pop();

      builder.push("Rooster");
      roosterSpawnEnable =
          builder.comment("Enable/Disable the rooster spawn.").define("roosterSpawnEnable", true);
      roosterMinGroup =
          builder.comment(MIN_GROUP_SIZE_TEXT).defineInRange("roosterMinGroup", 1, 0, 64);
      roosterMaxGroup =
          builder.comment(MAX_GROUP_SIZE_TEXT).defineInRange("roosterMaxGroup", 2, 0, 64);
      roosterWeight = builder.comment(SPAWN_WEIGHT_TEXT).defineInRange("roosterWeight", 6, 0, 100);
      builder.pop();

      builder.push("Small Slime");
      smallSlimeSpawnEnable = builder.comment("Enable/Disable the small slime spawn.")
          .define("smallSlimeSpawnEnable", true);
      smallSlimeMinGroup =
          builder.comment(MIN_GROUP_SIZE_TEXT).defineInRange("smallSlimeMinGroup", 1, 0, 64);
      smallSlimeMaxGroup =
          builder.comment(MAX_GROUP_SIZE_TEXT).defineInRange("smallSlimeMaxGroup", 2, 0, 64);
      smallSlimeWeight =
          builder.comment(SPAWN_WEIGHT_TEXT).defineInRange("smallSlimeWeight", 6, 0, 100);
      builder.pop();

      builder.push("Small Ghast");
      smallGhastExplosionPower =
          builder.comment("Explosion power").defineInRange("smallGhastExplosionPower", 0, 0, 16);
      smallGhastSpawnEnable = builder.comment("Enable/Disable the small ghast spawn.")
          .define("smallGhastSpawnEnable", true);
      smallGhastMinGroup =
          builder.comment(MIN_GROUP_SIZE_TEXT).defineInRange("smallGhastMinGroup", 1, 0, 64);
      smallGhastMaxGroup =
          builder.comment(MAX_GROUP_SIZE_TEXT).defineInRange("smallGhastMaxGroup", 2, 0, 64);
      smallGhastWeight =
          builder.comment(SPAWN_WEIGHT_TEXT).defineInRange("smallGhastWeight", 6, 0, 100);
      builder.pop();

      builder.push("Snail");
      snailSpawnEnable =
          builder.comment("Enable/Disable the snail spawn.").define("snailSpawnEnable", true);
      snailMinGroup = builder.comment(MIN_GROUP_SIZE_TEXT).defineInRange("snailMinGroup", 1, 0, 64);
      snailMaxGroup = builder.comment(MAX_GROUP_SIZE_TEXT).defineInRange("snailMaxGroup", 2, 0, 64);
      snailWeight = builder.comment(SPAWN_WEIGHT_TEXT).defineInRange("snailWeight", 6, 0, 100);
      builder.pop();

      builder.push("Welsh Corgi");
      welshCorgiSpawnEnable = builder.comment("Enable/Disable the welsh corgi spawn.")
          .define("welshCorgiSpawnEnable", true);
      welshCorgiMinGroup =
          builder.comment(MIN_GROUP_SIZE_TEXT).defineInRange("welshCorgiMinGroup", 1, 0, 64);
      welshCorgiMaxGroup =
          builder.comment(MAX_GROUP_SIZE_TEXT).defineInRange("welshCorgiMaxGroup", 2, 0, 64);
      welshCorgiWeight =
          builder.comment(SPAWN_WEIGHT_TEXT).defineInRange("welshCorgiWeight", 6, 0, 100);
      builder.pop();
    }
  }

  @SubscribeEvent
  public static void handleModConfigReloadEvent(ModConfigEvent.Reloading event) {
    ModConfig config = event.getConfig();
    if (config.getSpec() != commonSpec) {
      return;
    }
    log.info("Reload common config file {} ...", config.getFileName());
  }

}

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

package de.markusbordihn.playercompanions.client.renderer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.client.event.EntityRenderersEvent;

import de.markusbordihn.playercompanions.Constants;
import de.markusbordihn.playercompanions.client.model.SmallGhastModel;
import de.markusbordihn.playercompanions.client.model.SmallSlimeModel;
import de.markusbordihn.playercompanions.client.model.SnailModel;
import de.markusbordihn.playercompanions.entity.ModEntityType;

public class ClientRenderer {

  private static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  // Layer Definitions
  public static final ModelLayerLocation SMALL_GHAST =
      new ModelLayerLocation(new ResourceLocation(Constants.MOD_ID, "small_ghast"), "main");
  public static final ModelLayerLocation SMALL_SLIME =
      new ModelLayerLocation(new ResourceLocation(Constants.MOD_ID, "small_slime"), "small_slime");
  public static final ModelLayerLocation SMALL_SLIME_OUTER = new ModelLayerLocation(
      new ResourceLocation(Constants.MOD_ID, "small_slime"), "small_slime_outer");
  public static final ModelLayerLocation SNAIL =
      new ModelLayerLocation(new ResourceLocation(Constants.MOD_ID, "snail"), "main");

  protected ClientRenderer() {}

  public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
    log.info("{} Entity Renders ...", Constants.LOG_REGISTER_PREFIX);

    event.registerEntityRenderer(ModEntityType.SMALL_GHAST.get(), SmallGhastRenderer::new);
    event.registerEntityRenderer(ModEntityType.SMALL_SLIME.get(), SmallSlimeRenderer::new);
    event.registerEntityRenderer(ModEntityType.SNAIL.get(), SnailRenderer::new);
  }

  public static void registerEntityLayerDefinitions(
      EntityRenderersEvent.RegisterLayerDefinitions event) {
    log.info("{} Entity Layer Definitions ...", Constants.LOG_REGISTER_PREFIX);

    event.registerLayerDefinition(SMALL_GHAST, SmallGhastModel::createBodyLayer);
    event.registerLayerDefinition(SMALL_SLIME, SmallSlimeModel::createInnerBodyLayer);
    event.registerLayerDefinition(SMALL_SLIME_OUTER, SmallSlimeModel::createOuterBodyLayer);
    event.registerLayerDefinition(SNAIL, SnailModel::createBodyLayer);
  }
}

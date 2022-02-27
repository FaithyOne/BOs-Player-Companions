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

package de.markusbordihn.playercompanions.client.model;

import com.google.common.collect.Iterables;

import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.Entity;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PigModel<T extends Entity> extends QuadrupedModel<T> {

  private final ModelPart bags;

  public PigModel(ModelPart modelPart) {
    super(modelPart, false, 4.0F, 4.0F, 2.0F, 2.0F, 24);
    this.bags = modelPart.getChild("bags");
  }

  public static LayerDefinition createBodyLayer() {
    MeshDefinition meshDefinition = new MeshDefinition();
    PartDefinition partDefinition = meshDefinition.getRoot();

    // Body
    PartDefinition body = partDefinition.addOrReplaceChild("body", CubeListBuilder.create(),
        PartPose.offset(0.0F, 11.0F, 2.0F));
    body.addOrReplaceChild("body_main",
        CubeListBuilder.create().texOffs(26, 8).addBox(-5.0F, -10.0F, -7.0F, 10.0F, 16.0F, 8.0F,
            new CubeDeformation(0.0F)),
        PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

    // Bags
    PartDefinition bags = partDefinition.addOrReplaceChild("bags", CubeListBuilder.create()
        .texOffs(6, 20).addBox(5.0F, -14.0F, -3.0F, 2.0F, 9.0F, 8.0F, new CubeDeformation(0.0F))
        .texOffs(26, 32).addBox(-6.0F, -14.5F, -1.0F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
        .texOffs(26, 32).addBox(-6.0F, -14.5F, -1.0F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
        PartPose.offset(0.0F, 24.0F, 0.0F));
    bags.addOrReplaceChild("right_bag",
        CubeListBuilder.create().texOffs(6, 20).addBox(-1.0F, -4.5F, -4.0F, 2.0F, 9.0F, 8.0F,
            new CubeDeformation(0.0F)),
        PartPose.offsetAndRotation(-6.0F, -9.5F, 1.0F, 0.0F, 3.1416F, 0.0F));

    // Head with eyebrown's
    PartDefinition head = partDefinition.addOrReplaceChild("head", CubeListBuilder.create()
        .texOffs(0, 0).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
        .texOffs(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
        PartPose.offset(0.0F, 12.0F, -6.0F));
    head.addOrReplaceChild("eyebrown_right",
        CubeListBuilder.create().texOffs(0, 0).addBox(-1.025F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F,
            new CubeDeformation(0.0F)),
        PartPose.offsetAndRotation(2.975F, -1.975F, -8.0F, 0.0F, 0.0F, -0.0873F));
    head.addOrReplaceChild("eyebrown_left",
        CubeListBuilder.create().texOffs(0, 0).addBox(-1.9F, 0.025F, -0.5F, 2.0F, 1.0F, 1.0F,
            new CubeDeformation(0.0F)),
        PartPose.offsetAndRotation(-2.0F, -2.5F, -8.0F, 0.0F, 0.0F, 0.0873F));

    // Legs
    partDefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(0, 16)
        .addBox(-2.0F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
        PartPose.offset(-3.0F, 18.0F, -5.0F));
    partDefinition.addOrReplaceChild("left_front_leg",
        CubeListBuilder.create().texOffs(0, 16).mirror()
            .addBox(-1.0F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
        PartPose.offset(3.0F, 18.0F, -5.0F));
    partDefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(0, 16)
        .addBox(-2.0F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
        PartPose.offset(-3.0F, 18.0F, 6.0F));
    partDefinition.addOrReplaceChild("left_hind_leg",
        CubeListBuilder.create().texOffs(0, 16).mirror()
            .addBox(-1.0F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
        PartPose.offset(3.0F, 18.0F, 6.0F));
    return LayerDefinition.create(meshDefinition, 64, 64);
  }

  @Override
  protected Iterable<ModelPart> bodyParts() {
    return Iterables.concat(super.bodyParts(), java.util.List.of(this.bags));
  }

}
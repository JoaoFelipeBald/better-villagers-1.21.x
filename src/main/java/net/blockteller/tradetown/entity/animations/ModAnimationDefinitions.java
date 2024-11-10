package net.blockteller.tradetown.entity.animations;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class ModAnimationDefinitions {

    public static final AnimationDefinition NOOSE_ATTACK = AnimationDefinition.Builder.withLength(0.875f)
            .addAnimation("nose",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.875f, KeyframeAnimations.scaleVec(4.5f, 4.5f, 10.4f),
                                    AnimationChannel.Interpolations.LINEAR))).build();
    public static final AnimationDefinition WALK = AnimationDefinition.Builder.withLength(0f).build();
    public static final AnimationDefinition BONER = AnimationDefinition.Builder.withLength(0.8343334f)
            .addAnimation("nose",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.8343334f, KeyframeAnimations.posVec(0f, 3f, -2f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("nose",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0.19f, -0.66f, -0.68f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.8343334f, KeyframeAnimations.degreeVec(52.69f, -0.66f, -0.68f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("nose",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.8343334f, KeyframeAnimations.scaleVec(1f, 2.5f, 1f),
                                    AnimationChannel.Interpolations.LINEAR))).build();

}

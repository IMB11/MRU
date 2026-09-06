/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.cassian.mru.tags;

import cc.cassian.mru.util.CommonUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * See {@link net.minecraft.tags.BlockTags} for vanilla tags.
 * Note that addition to some vanilla tags implies having certain functionality.
 */
public final class CommonBlockTags {
	private CommonBlockTags() {
	}

	/**
	 * Natural stone-like blocks that can be used as a base ingredient in recipes that take stone.
	 */
	public static final TagKey<Block> STONES = CommonBlockItemTags.STONES.block();
	public static final TagKey<Block> COBBLESTONES = CommonBlockItemTags.COBBLESTONES.block();
	public static final TagKey<Block> DEEPSLATE_COBBLESTONES = CommonBlockItemTags.DEEPSLATE_COBBLESTONES.block();
	public static final TagKey<Block> INFESTED_COBBLESTONES = CommonBlockItemTags.INFESTED_COBBLESTONES.block();
	public static final TagKey<Block> MOSSY_COBBLESTONES = CommonBlockItemTags.MOSSY_COBBLESTONES.block();
	public static final TagKey<Block> NORMAL_COBBLESTONES = CommonBlockItemTags.NORMAL_COBBLESTONES.block();
	public static final TagKey<Block> NETHERRACKS = CommonBlockItemTags.NETHERRACKS.block();
	public static final TagKey<Block> END_STONES = CommonBlockItemTags.END_STONES.block();
	public static final TagKey<Block> GRAVELS = CommonBlockItemTags.GRAVELS.block();
	public static final TagKey<Block> OBSIDIANS = CommonBlockItemTags.OBSIDIANS.block();
	/**
	 * For common obsidian that has no special quirks or behaviors. Ideal for recipe use.
	 * Crying Obsidian, for example, is a light block and harder to obtain. So it gets its own tag instead of being under normal tag.
	 */
	public static final TagKey<Block> NORMAL_OBSIDIANS = CommonBlockItemTags.NORMAL_OBSIDIANS.block();
	public static final TagKey<Block> CRYING_OBSIDIANS = CommonBlockItemTags.CRYING_OBSIDIANS.block();
	/// Light-emitting blocks created when a Frog eats a Magma Cube.
	public static final TagKey<Block> FROGLIGHTS = CommonBlockItemTags.FROGLIGHTS.block();

	// Ores - broad categories
	public static final TagKey<Block> ORES = CommonBlockItemTags.ORES.block();

	// Ores - vanilla instances (All ores consolidated here for consistency)
	public static final TagKey<Block> COAL_ORES = CommonBlockItemTags.COAL_ORES.block();
	public static final TagKey<Block> COPPER_ORES = CommonBlockItemTags.COPPER_ORES.block();
	public static final TagKey<Block> DIAMOND_ORES = CommonBlockItemTags.DIAMOND_ORES.block();
	public static final TagKey<Block> EMERALD_ORES = CommonBlockItemTags.EMERALD_ORES.block();
	public static final TagKey<Block> GOLD_ORES = CommonBlockItemTags.GOLD_ORES.block();
	public static final TagKey<Block> IRON_ORES = CommonBlockItemTags.IRON_ORES.block();
	public static final TagKey<Block> LAPIS_ORES = CommonBlockItemTags.LAPIS_ORES.block();
	public static final TagKey<Block> NETHERITE_SCRAP_ORES = CommonBlockItemTags.NETHERITE_SCRAP_ORES.block();
	public static final TagKey<Block> QUARTZ_ORES = CommonBlockItemTags.QUARTZ_ORES.block();
	public static final TagKey<Block> REDSTONE_ORES = CommonBlockItemTags.REDSTONE_ORES.block();

	public static final TagKey<Block> BARRELS = CommonBlockItemTags.BARRELS.block();
	public static final TagKey<Block> WOODEN_BARRELS = CommonBlockItemTags.WOODEN_BARRELS.block();
	public static final TagKey<Block> BOOKSHELVES = CommonBlockItemTags.BOOKSHELVES.block();
	public static final TagKey<Block> CHESTS = CommonBlockItemTags.CHESTS.block();
	public static final TagKey<Block> WOODEN_CHESTS = CommonBlockItemTags.WOODEN_CHESTS.block();
	public static final TagKey<Block> TRAPPED_CHESTS = CommonBlockItemTags.TRAPPED_CHESTS.block();
	public static final TagKey<Block> ENDER_CHESTS = CommonBlockItemTags.ENDER_CHESTS.block();
	public static final TagKey<Block> GLASS_BLOCKS = CommonBlockItemTags.GLASS_BLOCKS.block();
	public static final TagKey<Block> GLASS_BLOCKS_COLORLESS = CommonBlockItemTags.GLASS_BLOCKS_COLORLESS.block();
	/**
	 * Glass which is made from cheap resources like sand and only minor additional ingredients like dyes.
	 */
	public static final TagKey<Block> GLASS_BLOCKS_CHEAP = CommonBlockItemTags.GLASS_BLOCKS_CHEAP.block();
	public static final TagKey<Block> GLASS_BLOCKS_TINTED = CommonBlockItemTags.GLASS_BLOCKS_TINTED.block();
	public static final TagKey<Block> GLASS_PANES = CommonBlockItemTags.GLASS_PANES.block();
	public static final TagKey<Block> GLASS_PANES_COLORLESS = CommonBlockItemTags.GLASS_PANES_COLORLESS.block();
	public static final TagKey<Block> GLAZED_TERRACOTTAS = CommonBlockItemTags.GLAZED_TERRACOTTAS.block();
	public static final TagKey<Block> CONCRETES = CommonBlockItemTags.CONCRETES.block();

	// Related to budding mechanics
	/**
	 * For blocks that are similar to amethyst where their budding block produces buds and cluster blocks.
	 */
	public static final TagKey<Block> BUDDING_BLOCKS = CommonBlockItemTags.BUDDING_BLOCKS.block();
	/**
	 * For blocks that are similar to amethyst where they have buddings forming from budding blocks.
	 */
	public static final TagKey<Block> BUDS = CommonBlockItemTags.BUDS.block();
	/**
	 * For blocks that are similar to amethyst where they have clusters forming from budding blocks.
	 */
	public static final TagKey<Block> CLUSTERS = CommonBlockItemTags.CLUSTERS.block();

	public static final TagKey<Block> VILLAGER_JOB_SITES = CommonBlockItemTags.VILLAGER_JOB_SITES.block();

	// Sand
	public static final TagKey<Block> SANDS = CommonBlockItemTags.SANDS.block();
	public static final TagKey<Block> RED_SANDS = CommonBlockItemTags.RED_SANDS.block();
	public static final TagKey<Block> COLORLESS_SANDS = CommonBlockItemTags.COLORLESS_SANDS.block();

	// Flower
	/**
	 * Contains living ground-based flowers that are 1 block tall such as Dandelions or Poppy.
	 * Equivalent to the "minecraft:small_flowers" block tag.
	 * This is NOT aliased with {@link BlockTags#SMALL_FLOWERS} because the vanilla tag is used to make the block weak to swords.
	 */
	public static final TagKey<Block> SMALL_FLOWERS = CommonBlockItemTags.SMALL_FLOWERS.block();
	/**
	 * Contains living ground-based flowers that are 2 block tall such as Rose Bush or Peony.
	 * Equivalent to the "minecraft:tall_flowers" block tag in past Minecraft version.
	 */
	public static final TagKey<Block> TALL_FLOWERS = CommonBlockItemTags.TALL_FLOWERS.block();
	/**
	 * Contains any living plant block that contains flowers or is a flower itself.
	 * Equivalent to the "minecraft:flowers" block tag.
	 * Aliased with {@link BlockTags#FLOWERS}.
	 */
	public static final TagKey<Block> FLOWERS = CommonBlockItemTags.FLOWERS.block();

	// Sandstone
	public static final TagKey<Block> SANDSTONE_BLOCKS = CommonBlockItemTags.SANDSTONE_BLOCKS.block();
	public static final TagKey<Block> SANDSTONE_SLABS = CommonBlockItemTags.SANDSTONE_SLABS.block();
	public static final TagKey<Block> SANDSTONE_STAIRS = CommonBlockItemTags.SANDSTONE_STAIRS.block();
	public static final TagKey<Block> RED_SANDSTONE_BLOCKS = CommonBlockItemTags.RED_SANDSTONE_BLOCKS.block();
	public static final TagKey<Block> RED_SANDSTONE_SLABS = CommonBlockItemTags.RED_SANDSTONE_SLABS.block();
	public static final TagKey<Block> RED_SANDSTONE_STAIRS = CommonBlockItemTags.RED_SANDSTONE_STAIRS.block();
	public static final TagKey<Block> UNCOLORED_SANDSTONE_BLOCKS = CommonBlockItemTags.UNCOLORED_SANDSTONE_BLOCKS.block();
	public static final TagKey<Block> UNCOLORED_SANDSTONE_SLABS = CommonBlockItemTags.UNCOLORED_SANDSTONE_SLABS.block();
	public static final TagKey<Block> UNCOLORED_SANDSTONE_STAIRS = CommonBlockItemTags.UNCOLORED_SANDSTONE_STAIRS.block();

	// Fences and Fence Gates
	/**
	 * Aliased with {@link BlockTags#FENCES}.
	 */
	public static final TagKey<Block> FENCES = CommonBlockItemTags.FENCES.block();
	/**
	 * Aliased with {@link BlockTags#WOODEN_FENCES}.
	 */
	public static final TagKey<Block> WOODEN_FENCES = CommonBlockItemTags.WOODEN_FENCES.block();
	public static final TagKey<Block> NETHER_BRICK_FENCES = CommonBlockItemTags.NETHER_BRICK_FENCES.block();
	/**
	 * Aliased with {@link BlockTags#FENCE_GATES}.
	 */
	public static final TagKey<Block> FENCE_GATES = CommonBlockItemTags.FENCE_GATES.block();
	public static final TagKey<Block> WOODEN_FENCE_GATES = CommonBlockItemTags.WOODEN_FENCE_GATES.block();

	// Bars
	/**
	 * Aliased with {@link BlockTags#BARS}.
	 */
	public static final TagKey<Block> BARS = CommonBlockItemTags.BARS.block();
	public static final TagKey<Block> IRON_BARS = CommonBlockItemTags.IRON_BARS.block();
	public static final TagKey<Block> COPPER_BARS = CommonBlockItemTags.COPPER_BARS.block();

	// Pumpkins
	public static final TagKey<Block> PUMPKINS = CommonBlockItemTags.PUMPKINS.block();
	/**
	 * For pumpkins that are not carved.
	 */
	public static final TagKey<Block> NORMAL_PUMPKINS = CommonBlockItemTags.NORMAL_PUMPKINS.block();
	/**
	 * For pumpkins that are already carved but not a light source.
	 */
	public static final TagKey<Block> CARVED_PUMPKINS = CommonBlockItemTags.CARVED_PUMPKINS.block();

	/**
	 * For pumpkins that are already carved and a light source.
	 */
	public static final TagKey<Block> JACK_O_LANTERNS_PUMPKINS = CommonBlockItemTags.JACK_O_LANTERNS_PUMPKINS.block();

	// Blocks created with dyes
	/**
	 * Tag that holds all blocks which can be dyed a specific color.
	 * (Does not include color blending blocks which would behave similarly to leather armor items)
	 */
	public static final TagKey<Block> DYED = CommonBlockItemTags.DYED.block();
	public static final TagKey<Block> BLACK_DYED = CommonBlockItemTags.BLACK_DYED.block();
	public static final TagKey<Block> BLUE_DYED = CommonBlockItemTags.BLUE_DYED.block();
	public static final TagKey<Block> BROWN_DYED = CommonBlockItemTags.BROWN_DYED.block();
	public static final TagKey<Block> CYAN_DYED = CommonBlockItemTags.CYAN_DYED.block();
	public static final TagKey<Block> GRAY_DYED = CommonBlockItemTags.GRAY_DYED.block();
	public static final TagKey<Block> GREEN_DYED = CommonBlockItemTags.GREEN_DYED.block();
	public static final TagKey<Block> LIGHT_BLUE_DYED = CommonBlockItemTags.LIGHT_BLUE_DYED.block();
	public static final TagKey<Block> LIGHT_GRAY_DYED = CommonBlockItemTags.LIGHT_GRAY_DYED.block();
	public static final TagKey<Block> LIME_DYED = CommonBlockItemTags.LIME_DYED.block();
	public static final TagKey<Block> MAGENTA_DYED = CommonBlockItemTags.MAGENTA_DYED.block();
	public static final TagKey<Block> ORANGE_DYED = CommonBlockItemTags.ORANGE_DYED.block();
	public static final TagKey<Block> PINK_DYED = CommonBlockItemTags.PINK_DYED.block();
	public static final TagKey<Block> PURPLE_DYED = CommonBlockItemTags.PURPLE_DYED.block();
	public static final TagKey<Block> RED_DYED = CommonBlockItemTags.RED_DYED.block();
	public static final TagKey<Block> WHITE_DYED = CommonBlockItemTags.WHITE_DYED.block();
	public static final TagKey<Block> YELLOW_DYED = CommonBlockItemTags.YELLOW_DYED.block();

	/**
	 * Tag that holds blocks which can be dyed but do not have their own color already, like glass.
	 * (Does not include color blending blocks which would behave similarly to leather armor items)
	 */
	public static final TagKey<Block> UNDYED_SIMPLE_DYEABLE = CommonBlockItemTags.UNDYED_SIMPLE_DYEABLE.block();

	/**
	 * Tag that holds blocks which can be dyed despite already having a color, like wool.
	 * (Does not include color blending blocks which would behave similarly to leather armor items)
	 */
	public static final TagKey<Block> REDYEABLE_SIMPLE_DYEABLE = CommonBlockItemTags.REDYEABLE_SIMPLE_DYEABLE.block();

	/**
	 * Tag that holds blocks which can be dyed in a simple fashion without color blending, typically
	 * in the standard 16 colors, whether they have a color already or not.
	 */
	public static final TagKey<Block> SIMPLE_DYEABLE = CommonBlockItemTags.SIMPLE_DYEABLE.block();

	/**
	 * Tag that holds blocks which can be dyed in a dynamic color blending fashion, similarly to leather armor items.
	 */
	public static final TagKey<Block> DYNAMIC_DYEABLE = CommonBlockItemTags.DYNAMIC_DYEABLE.block();

	/**
	 * Tag that holds blocks which can have dye applied to them, whether they have a color already or not.
	 */
	public static final TagKey<Block> DYEABLE = CommonBlockItemTags.DYEABLE.block();

	// Blocks that are for storing resources
	/**
	 * A storage block is generally a block that has a recipe to craft a bulk of 1 kind of resource to a block
	 * and has a mirror recipe to reverse the crafting with no loss in resources.
	 *
	 * <p>Honey Block is special in that the reversing recipe is not a perfect mirror of the crafting recipe
	 * and so, it is considered a special case and not given a storage block tag.
	 */
	public static final TagKey<Block> STORAGE_BLOCKS = CommonBlockItemTags.STORAGE_BLOCKS.block();
	public static final TagKey<Block> STORAGE_BLOCKS_BONE_MEAL = CommonBlockItemTags.STORAGE_BLOCKS_BONE_MEAL.block();
	public static final TagKey<Block> STORAGE_BLOCKS_COAL = CommonBlockItemTags.STORAGE_BLOCKS_COAL.block();
	public static final TagKey<Block> STORAGE_BLOCKS_COPPER = CommonBlockItemTags.STORAGE_BLOCKS_COPPER.block();
	public static final TagKey<Block> STORAGE_BLOCKS_DIAMOND = CommonBlockItemTags.STORAGE_BLOCKS_DIAMOND.block();
	public static final TagKey<Block> STORAGE_BLOCKS_DRIED_KELP = CommonBlockItemTags.STORAGE_BLOCKS_DRIED_KELP.block();
	public static final TagKey<Block> STORAGE_BLOCKS_EMERALD = CommonBlockItemTags.STORAGE_BLOCKS_EMERALD.block();
	public static final TagKey<Block> STORAGE_BLOCKS_GOLD = CommonBlockItemTags.STORAGE_BLOCKS_GOLD.block();
	public static final TagKey<Block> STORAGE_BLOCKS_IRON = CommonBlockItemTags.STORAGE_BLOCKS_IRON.block();
	public static final TagKey<Block> STORAGE_BLOCKS_LAPIS = CommonBlockItemTags.STORAGE_BLOCKS_LAPIS.block();
	public static final TagKey<Block> STORAGE_BLOCKS_NETHERITE = CommonBlockItemTags.STORAGE_BLOCKS_NETHERITE.block();
	public static final TagKey<Block> STORAGE_BLOCKS_RAW_COPPER = CommonBlockItemTags.STORAGE_BLOCKS_RAW_COPPER.block();
	public static final TagKey<Block> STORAGE_BLOCKS_RAW_GOLD = CommonBlockItemTags.STORAGE_BLOCKS_RAW_GOLD.block();
	public static final TagKey<Block> STORAGE_BLOCKS_RAW_IRON = CommonBlockItemTags.STORAGE_BLOCKS_RAW_IRON.block();
	public static final TagKey<Block> STORAGE_BLOCKS_REDSTONE = CommonBlockItemTags.STORAGE_BLOCKS_REDSTONE.block();
	public static final TagKey<Block> STORAGE_BLOCKS_RESIN = CommonBlockItemTags.STORAGE_BLOCKS_RESIN.block();
	public static final TagKey<Block> STORAGE_BLOCKS_SLIME = CommonBlockItemTags.STORAGE_BLOCKS_SLIME.block();
	public static final TagKey<Block> STORAGE_BLOCKS_WHEAT = CommonBlockItemTags.STORAGE_BLOCKS_WHEAT.block();

	// Logs
	/**
	 * For logs found naturally in the Overworld, does not include Stripped Logs.
	 * Aliased with {@link BlockTags#OVERWORLD_NATURAL_LOGS} for consistency.
	 */
	public static final TagKey<Block> OVERWORLD_NATURAL_LOGS = CommonBlockItemTags.OVERWORLD_NATURAL_LOGS.block();
	/**
	 * For logs, including Stems, found naturally in the Nether, does not include Stripped Logs.
	 */
	public static final TagKey<Block> NETHER_NATURAL_LOGS = CommonBlockItemTags.NETHER_NATURAL_LOGS.block();
	/**
	 * For logs, including Stems, found naturally that have not been stripped.
	 */
	public static final TagKey<Block> NATURAL_LOGS = CommonBlockItemTags.NATURAL_LOGS.block();
	/**
	 * For six-sided wood blocks, including Hyphae, found naturally that have not been stripped.
	 */
	public static final TagKey<Block> NATURAL_WOODS = CommonBlockItemTags.NATURAL_WOODS.block();
	/**
	 * For logs, including Stems, found naturally that have been stripped.
	 */
	public static final TagKey<Block> STRIPPED_LOGS = CommonBlockItemTags.STRIPPED_LOGS.block();
	/**
	 * For six-sided wood blocks, including Hyphae, found naturally that have been stripped.
	 */
	public static final TagKey<Block> STRIPPED_WOODS = CommonBlockItemTags.STRIPPED_WOODS.block();

	// Misc
	public static final TagKey<Block> PLAYER_WORKSTATIONS_CRAFTING_TABLES = CommonBlockItemTags.PLAYER_WORKSTATIONS_CRAFTING_TABLES.block();
	public static final TagKey<Block> PLAYER_WORKSTATIONS_FURNACES = CommonBlockItemTags.PLAYER_WORKSTATIONS_FURNACES.block();
	/**
	 * Blocks should be included in this tag if their movement/relocation can cause serious issues such
	 * as world corruption upon being moved or for balance reason where the block should not be able to be relocated.
	 * Example: Chunk loaders or pipes where other mods that move blocks do not respect
	 * {@link BlockBehaviour.BlockStateBase#getPistonPushReaction}.
	 */
	public static final TagKey<Block> RELOCATION_NOT_SUPPORTED = register("relocation_not_supported");
	//? if >1.21 {
	/**
	 * Tag that holds all head based blocks such as Skeleton Skull or Player Head. (Named skulls to match minecraft:skulls item tag)
	 */
	public static final TagKey<Block> SKULLS = CommonBlockItemTags.SKULLS.block();
	//?}
	public static final TagKey<Block> ROPES = CommonBlockItemTags.ROPES.block();
	public static final TagKey<Block> CHAINS = CommonBlockItemTags.CHAINS.block();

	/**
	 * Tag that holds all blocks that recipe viewers should not show to users.
	 * Recipe viewers may use this to automatically find the corresponding BlockItem to hide.
	 */
	public static final TagKey<Block> HIDDEN_FROM_RECIPE_VIEWERS = CommonBlockItemTags.HIDDEN_FROM_RECIPE_VIEWERS.block();

	/**
	 * Blocks which are often replaced by deepslate ores, i.e. the ores in the tag {@link #ORES_IN_GROUND_DEEPSLATE}, during world generation.
	 * (The block's registry name is used as the tag name)
	 */
	public static final TagKey<Block> ORE_BEARING_GROUND_DEEPSLATE = CommonBlockItemTags.ORE_BEARING_GROUND_DEEPSLATE.block();
	/**
	 * Blocks which are often replaced by netherrack ores, i.e. the ores in the tag {@link #ORES_IN_GROUND_NETHERRACK}, during world generation.
	 * (The block's registry name is used as the tag name)
	 */
	public static final TagKey<Block> ORE_BEARING_GROUND_NETHERRACK = CommonBlockItemTags.ORE_BEARING_GROUND_NETHERRACK.block();
	/**
	 * Blocks which are often replaced by stone ores, i.e. the ores in the tag {@link #ORES_IN_GROUND_STONE}, during world generation.
	 * (The block's registry name is used as the tag name)
	 */
	public static final TagKey<Block> ORE_BEARING_GROUND_STONE = CommonBlockItemTags.ORE_BEARING_GROUND_STONE.block();
	/**
	 * Ores which on average result in more than one resource worth of materials ignoring fortune and other modifiers.
	 * (example, Copper Ore)
	 */
	public static final TagKey<Block> ORE_RATES_DENSE = CommonBlockItemTags.ORE_RATES_DENSE.block();
	/**
	 * Ores which on average result in one resource worth of materials ignoring fortune and other modifiers.
	 * (Example, Iron Ore)
	 */
	public static final TagKey<Block> ORE_RATES_SINGULAR = CommonBlockItemTags.ORE_RATES_SINGULAR.block();
	/**
	 * Ores which on average result in less than one resource worth of materials ignoring fortune and other modifiers.
	 * (Example, Nether Gold Ore as it drops 2 to 6 Gold Nuggets which is less than normal Gold Ore's Raw Gold drop)
	 */
	public static final TagKey<Block> ORE_RATES_SPARSE = CommonBlockItemTags.ORE_RATES_SPARSE.block();
	/**
	 * Ores in deepslate (or in equivalent blocks in the tag {@link #ORE_BEARING_GROUND_DEEPSLATE}) which could logically use deepslate as recipe input or output.
	 * (The block's registry name is used as the tag name)
	 */
	public static final TagKey<Block> ORES_IN_GROUND_DEEPSLATE = CommonBlockItemTags.ORES_IN_GROUND_DEEPSLATE.block();
	/**
	 * Ores in netherrack (or in equivalent blocks in the tag {@link #ORE_BEARING_GROUND_NETHERRACK}) which could logically use netherrack as recipe input or output.
	 * (The block's registry name is used as the tag name)
	 */
	public static final TagKey<Block> ORES_IN_GROUND_NETHERRACK = CommonBlockItemTags.ORES_IN_GROUND_NETHERRACK.block();
	/**
	 * Ores in stone (or in equivalent blocks in the tag {@link #ORE_BEARING_GROUND_STONE}) which could logically use stone as recipe input or output.
	 * (The block's registry name is used as the tag name)
	 */
	public static final TagKey<Block> ORES_IN_GROUND_STONE = CommonBlockItemTags.ORES_IN_GROUND_STONE.block();

	private static TagKey<Block> register(String tagId) {
		return CommonUtils.blockTag("c", tagId);
	}
}
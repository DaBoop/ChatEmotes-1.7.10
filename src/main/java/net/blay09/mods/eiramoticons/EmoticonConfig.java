// Copyright (c) 2015, Christopher "blay09" Baker
// Some rights reserved.

package net.blay09.mods.eiramoticons;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class EmoticonConfig {

	public static final String GENERAL = "general";
	public static final String PACKS = "packs";
	public static final String TWEAKS = "tweaks";

	public static Configuration config;
	public static File configFile;

	public static boolean enableMCEmotes;
	public static boolean enableIRCEmotes;
	public static String twitchSubscriberRegex;
	public static int twitchSmileySet;

	public static boolean defaultPack;
	public static boolean animuPack;
	public static boolean userPack;
	public static boolean twitchGlobalEmotes;
	public static boolean twitchTurboEmotes;
	public static boolean twitchSubscriberEmotes;
	public static boolean bttvEmotes;
	public static boolean twitchSmileys;

	public static boolean betterKappas;

	public static void loadFromFile(File configFile) {
		EmoticonConfig.configFile = configFile;
		config = new Configuration(configFile);
		loadFromConfig();
	}

	private static void loadFromConfig() {
		enableMCEmotes = config.getBoolean("enableMCEmotes", GENERAL, true, I18n.format("eiramoticons:config.enableMCEmotes.tooltip"), "eiramoticons:config.enableMCEmotes");
		twitchSubscriberRegex = config.getString("twitchSubscriberRegex", GENERAL, "[a-z0-9][a-z0-9]+[A-Z].*", I18n.format("eiramoticons:config.twitchSubscriberRegex.tooltip"), "eiramoticons:config.twitchSubscriberRegex");
		twitchSmileySet = config.getInt("twitchSmileySet", GENERAL, 0, 0, 2, I18n.format("eiramoticons:config.twitchSmileySet.tooltip"), "eiramoticons:config.twitchSmileySet");
		enableIRCEmotes = config.getBoolean("enableIRCEmotes", GENERAL, true, I18n.format("eiramoticons:config.enableIRCEmotes.tooltip"), "eiramoticons:config.enableIRCEmotes");

		twitchGlobalEmotes = config.getBoolean("twitchGlobalEmotes", PACKS, true, I18n.format("eiramoticons:config.twitchGlobalEmotes.tooltip"), "eiramoticons:config.twitchGlobalEmotes");
		twitchTurboEmotes = config.getBoolean("twitchTurboEmotes", PACKS, false, I18n.format("eiramoticons:config.twitchTurboEmotes.tooltip"), "eiramoticons:config.twitchTurboEmotes");
		twitchSubscriberEmotes = config.getBoolean("twitchSubscriberEmotes", PACKS, true, I18n.format("eiramoticons:config.twitchSubscriberEmotes.tooltip"), "eiramoticons:config.twitchSubscriberEmotes");
		bttvEmotes = config.getBoolean("bttvEmotes", PACKS, false, I18n.format("eiramoticons:config.bttvEmotes.tooltip"), "eiramoticons:config.bttvEmotes");
		twitchSmileys = config.getBoolean("twitchSmileys", PACKS, false, I18n.format("eiramoticons:config.twitchSmileys.tooltip"), "eiramoticons:config.twitchSmileys");
		defaultPack = config.getBoolean("defaultPack", PACKS, true, I18n.format("eiramoticons:config.defaultPack.tooltip"), "eiramoticons:config.defaultPack");
		animuPack = config.getBoolean("animuPack", PACKS, true, I18n.format("eiramoticons:config.animuPack.tooltip"), "eiramoticons:config.animuPack");
		userPack = config.getBoolean("userPack", PACKS, true, I18n.format("eiramoticons:config.userPack.tooltip"), "eiramoticons:config.userPack");

		betterKappas = config.getBoolean("betterKappas", TWEAKS, false, I18n.format("eiramoticons:config.betterKappas.tooltip"), "eiramoticons:config.betterKappas");

		config.save();
	}

	public static void lightReload() {
		loadFromConfig();
	}

	public static void hardReload() {
		loadFromFile(configFile);
	}
}

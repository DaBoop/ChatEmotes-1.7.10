// Copyright (c) 2015, Christopher "blay09" Baker
// Some rights reserved.

package net.blay09.mods.eiramoticons.emoticon;

import net.blay09.mods.eiramoticons.api.IEmoticon;
import net.blay09.mods.eiramoticons.api.IEmoticonLoader;
import net.blay09.mods.eiramoticons.api.ReloadEmoticons;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IntHashMap;
import net.minecraftforge.common.MinecraftForge;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class EmoticonRegistry {

	private static final AtomicInteger idCounter = new AtomicInteger();
	private static final IntHashMap emoticonMap = new IntHashMap();
	private static final Map<String, Emoticon> namedMap = new HashMap<String, Emoticon>();
	private static final Map<String, EmoticonGroup> groupMap = new HashMap<String, EmoticonGroup>();
	private static final List<Emoticon> disposalList = new ArrayList<>();

	public static IEmoticon registerEmoticon(String name, IEmoticonLoader loader) {
		Emoticon emoticon = new Emoticon(idCounter.incrementAndGet(), name, loader);
		emoticonMap.addKey(emoticon.id, emoticon);
		namedMap.put(emoticon.code, emoticon);
		return emoticon;
	}

	public static Collection<EmoticonGroup> getGroups() {
		return groupMap.values();
	}

	public static EmoticonGroup registerEmoticonGroup(String groupName, IChatComponent listComponent) {
		EmoticonGroup group = new EmoticonGroup(groupName, listComponent);
		groupMap.put(groupName, group);
		return group;
	}

	public static Emoticon fromName(String name) {
		return namedMap.get(name);
	}

	public static Emoticon fromId(int id) {
		return (Emoticon) emoticonMap.lookup(id);
	}

	public static void reloadEmoticons() {
		synchronized (disposalList) {
			for (Emoticon emoticon : namedMap.values()) {
				disposalList.add(emoticon);
			}
		}
		idCounter.set(0);
		emoticonMap.clearMap();
		groupMap.clear();
		namedMap.clear();
		MinecraftForge.EVENT_BUS.post(new ReloadEmoticons());
	}

	public static void runDisposal() {
		synchronized (disposalList) {
			if (!disposalList.isEmpty()) {
				for (Emoticon emoticon : disposalList) {
					emoticon.disposeTexture();
				}
				disposalList.clear();
			}
		}
	}
}

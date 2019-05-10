package br.com.kickpost.harleymobs.ranks.listener;

import br.com.devpaulo.legendchat.api.events.*;
import br.com.kickpost.harleymobs.ranks.dao.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class onPlayerChatListener implements Listener
{
    @EventHandler
    public void onChat(final ChatMessageEvent e) {
        if (e.getTags().contains("rank")) {
            final Player player = e.getSender();
            e.setTagValue("rank", UserDao.get(player).getRank().getTag());
        }
    }
}

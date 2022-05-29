package fr.pablozapata.ncsbot.events;

import fr.pablozapata.ncsbot.events.listeners.*;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventHandler extends ListenerAdapter {
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        new Ready(event);
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        final var author = event.getAuthor();
        if (author.isBot() || author.isSystem() || author.equals(event.getJDA().getSelfUser())) {
            return;
        }
        new MessageReceived(event);
    }

    @Override
    public void onMessageUpdate(@NotNull MessageUpdateEvent event) {
        final var author = event.getAuthor();
        if (author.isBot() || author.isSystem() || author.equals(event.getJDA().getSelfUser())) {
            return;
        }
        new MessageUpdate(event);
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        new GuildMemberJoin(event);
    }

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {
        new GuildMemberRemove(event);
    }
}

package fr.pablozapata.ncsbot.utils;

import fr.pablozapata.ncsbot.Main;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;

public class MessageUtils {
    public static Boolean inviteCheck(MessageReceivedEvent event) {
        final var member = event.getMember();
        final var selfMember = Main.getNcsGuild().getSelfMember();

        if (!selfMember.canInteract(member)) {
            return false;
        }

        for (String inviteCode : event.getMessage().getInvites()) {
            final Invite invite;

            try {
                invite = Invite.resolve(Main.getBot(), inviteCode).complete();
            } catch (ErrorResponseException e) {
                continue;
            }

            if (invite.getCode().equals(Main.getNcsGuild().getVanityCode())) {
                continue;
            }

            if (invite.getGuild().getIdLong() != Main.getNcsGuild().getIdLong()) {
                return true;
            }
        }

        return false;
    }

    public static Boolean inviteCheck(MessageUpdateEvent event) {
        final var member = event.getMember();
        final var selfMember = Main.getNcsGuild().getSelfMember();

        if (!selfMember.canInteract(member)) {
            return false;
        }

        for (String inviteCode : event.getMessage().getInvites()) {
            final Invite invite;

            try {
                invite = Invite.resolve(Main.getBot(), inviteCode).complete();
            } catch (ErrorResponseException e) {
                continue;
            }

            if (invite.getCode().equals(Main.getNcsGuild().getVanityCode())) {
                continue;
            }

            if (invite.getGuild().getIdLong() != Main.getNcsGuild().getIdLong()) {
                return true;
            }
        }

        return false;
    }
}

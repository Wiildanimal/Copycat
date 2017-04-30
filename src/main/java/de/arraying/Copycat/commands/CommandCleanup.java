package de.arraying.Copycat.commands;

import de.arraying.Copycat.Copycat;
import de.arraying.Copycat.Messages;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2017 Arraying
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class CommandCleanup extends Command {

    /**
     * Readies the cleanup command.
     */
    public CommandCleanup() {
        super("cleanup", "command.cleanup.description", Permission.MESSAGE_WRITE, "cleanup", true);
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        if(Copycat.getInstance().getDataConfig().isBotBeta()) {
            e.getChannel().sendMessage(Messages.get(e.getGuild(), "command.cleanup.beta")).queue();
            return;
        }
        List<String> unused = new ArrayList<>(Copycat.getInstance().getLocalGuilds().keySet());
        Copycat.getInstance().getJda().getGuilds().forEach(guild -> unused.remove(guild.getId()));
        unused.forEach(id -> Copycat.getInstance().getDataManager().removeGuild(id));
        e.getChannel().sendMessage(Messages.get(e.getGuild(), "command.cleanup.cleanup")
                .replace("{guilds}", String.valueOf(unused.size()))).queue();
    }

}

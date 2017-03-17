package de.arraying.Copycat.parameters;

import de.arraying.Copycat.data.DataSay;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

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
public class ParameterSilent extends Parameter {

    /**
     * The silent parameter (-s) does not send the
     * "The message has been sent" message when
     * the message is sent.
     */
    public ParameterSilent() {
        super("-s");
    }

    @Override
    public String invoke(GuildMessageReceivedEvent e, String input) {
        DataSay.retrieve(e.getMessage().getId()).setSilent(true);
        return input.replace(getTrigger(), "");
    }

}

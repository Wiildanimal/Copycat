package de.arraying.Copycat.parameters;

import de.arraying.Copycat.data.DataSay;
import de.arraying.Copycat.utils.Utils;
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
public class ParameterTime extends Parameter {

    /**
     * The time parameter (--t) sets a timer as to when
     * the message should get sent.
     */
    public ParameterTime() {
        super("--t");
    }

    /**
     * Invokes the parameter.
     * @param event The chat event.
     * @param input The current input.
     * @return The string after it has been modified.
     */
    @Override
    public String invoke(GuildMessageReceivedEvent event, String input) {
        String value = Utils.getInstance().getParameterValue(input, getTrigger());
        if(!value.equalsIgnoreCase("")) {
            if(Utils.getInstance().isInt(value)) {
                int delay = Integer.valueOf(value);
                if(delay > 0
                        && delay <= 3600) {
                    DataSay.retrieve(event.getMessage().getId()).setDelay(delay);
                }
            }
        }
        return input.replace(getTrigger()+" "+value, "");
    }

}

/*
 * Password Management Servlets (PWM)
 * http://www.pwm-project.org
 *
 * Copyright (c) 2006-2009 Novell, Inc.
 * Copyright (c) 2009-2019 The PWM Project
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

package password.pwm.util.java;

import password.pwm.error.PwmException;
import password.pwm.util.logging.PwmLogger;

import java.util.function.Consumer;

@FunctionalInterface
public interface PwmExceptionLoggingConsumer<T>
{
    static <T> Consumer<T> wrapConsumer( final PwmExceptionLoggingConsumer<T> throwingConsumer )
    {
        return t ->
        {
            try
            {
                throwingConsumer.accept( t );
            }
            catch ( final Exception e )
            {
                final PwmLogger pwmLogger = PwmLogger.forClass( throwingConsumer.getClass() );
                pwmLogger.error( () -> "unexpected error running '" + throwingConsumer.getClass().getName()
                        + "'  consumer: " + e.getMessage(), e );
            }
        };
    }

    void accept( T t ) throws PwmException;
}

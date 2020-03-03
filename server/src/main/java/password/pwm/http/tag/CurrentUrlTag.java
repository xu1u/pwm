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

package password.pwm.http.tag;

import password.pwm.http.JspUtility;
import password.pwm.http.PwmRequest;
import password.pwm.util.java.StringUtil;
import password.pwm.util.logging.PwmLogger;

import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class CurrentUrlTag extends TagSupport
{
    private static final PwmLogger LOGGER = PwmLogger.forClass( CurrentUrlTag.class );

    @Override
    public int doEndTag( )
            throws javax.servlet.jsp.JspTagException
    {
        try
        {
            final PwmRequest pwmRequest = JspUtility.getPwmRequest( pageContext );
            final String currentUrl = pwmRequest.getURLwithoutQueryString();
            pageContext.getOut().write( StringUtil.escapeHtml( currentUrl ) );
        }
        catch ( final Exception e )
        {
            try
            {
                pageContext.getOut().write( "errorGeneratingPwmFormID" );
            }
            catch ( final IOException e1 )
            {
                /* ignore */
            }
            LOGGER.error( () -> "error during pwmFormIDTag output of pwmFormID: " + e.getMessage() );
        }
        return EVAL_PAGE;
    }
}

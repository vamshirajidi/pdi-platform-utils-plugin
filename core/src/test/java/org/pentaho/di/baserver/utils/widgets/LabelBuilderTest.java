/*
 * This program is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License, version 2 as published by the Free Software
 * Foundation.
 *
 * You should have received a copy of the GNU General Public License along with this
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/gpl-2.0.html
 * or from the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 *
 * Copyright 2006 - 2024 Hitachi Vantara.  All rights reserved.
 */

package org.pentaho.di.baserver.utils.widgets;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.pentaho.di.ui.core.PropsUI;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;


public class LabelBuilderTest {
  LabelBuilder labelBuilder, labelBuilderSpy;
  Composite parent = mock( Composite.class );
  PropsUI propsUI = mock( PropsUI.class );

  @Before
  public void setUp() throws Exception {
    labelBuilder = new LabelBuilder( parent, propsUI );
    labelBuilderSpy = spy( labelBuilder );
  }

  @Test
  public void testSetText() throws Exception {
    assertEquals( "", labelBuilder.getText() ); //$NON-NLS-1$
    String labelText = "new-label-text"; //$NON-NLS-1$
    labelBuilder.setText( labelText );
    assertEquals( labelText, labelBuilder.getText() );
  }

  @Test
  public void testCreateWidget() throws Exception {
    String text = "label-text"; //$NON-NLS-1$
    Label labelMock = mock( Label.class );
    doReturn( labelMock ).when( labelBuilderSpy ).createLabel( Mockito.<Composite>any(), anyInt() );
    doReturn( text ).when( labelMock ).getText();

    labelBuilderSpy.setText( text );
    Label label = labelBuilderSpy.createWidget( parent );

    assertEquals( text, label.getText() );
  }
}

/***********************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Actuate Corporation - initial API and implementation
 ***********************************************************************/

package org.eclipse.birt.report.engine.nLayout.area.impl;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.engine.api.IEngineTask;
import org.eclipse.birt.report.engine.api.InstanceID;
import org.eclipse.birt.report.engine.content.IBandContent;
import org.eclipse.birt.report.engine.content.IContent;
import org.eclipse.birt.report.engine.content.IElement;
import org.eclipse.birt.report.engine.content.ITextContent;
import org.eclipse.birt.report.engine.content.impl.AbstractBandContent;
import org.eclipse.birt.report.engine.nLayout.LayoutContext;
import org.eclipse.birt.report.engine.nLayout.area.ILayout;

public class InlineTextArea extends InlineContainerArea implements ILayout
{	
	private InlineTextRenderListener listener = null;
	
	public InlineTextArea( ContainerArea parent, LayoutContext context,
			IContent content )
	{
		super( parent, context, content );
		if ( context.isInHtmlRender( ) )
		{
			InstanceID id = content.getInstanceID( );

			if ( id != null )
			{
				SizeBasedContent hint = (SizeBasedContent) context
						.getHtmlLayoutContext( ).getPageHintManager( )
						.getSizeBasedContentMapping( )
						.get( id.toUniqueString( ) );
				if ( hint != null )
				{
					this.setX( hint.floatPos );
					listener = new InlineTextRenderListener( this,
							hint.offsetInContent, hint.dimension );
				}
			}
		}
	}
	
	public InlineTextArea( InlineTextArea area )
	{
		super( area );
	}

	public void layout( ) throws BirtException
	{
		initialize( );
		removeHyperlinkForBlankText( );
		TextAreaLayout inlineText = new TextAreaLayout( this, context, content );
		inlineText.initialize( );
		if ( context.isInHtmlRender( ) )
		{
			inlineText.addListener( listener );
		}
		inlineText.layout( );
		inlineText.close( );
		updateTextContent( );
		close( );
	}
	
	private void removeHyperlinkForBlankText( )
	{
		String text = ( (ITextContent) content ).getText( );
		if ( text == null || text.length( ) == 0 )
		{
			setAction( null );
		}
	}
	
	public InlineTextArea cloneArea( )
	{
		InlineTextArea newArea = new InlineTextArea( this );
		return newArea;
	}
	
	protected void addToExtension( InlineContainerArea area )
	{
		addLineToExtension( (InlineTextArea)area );
	}
	
	
	private void addLineToExtension( InlineTextArea area )
	{
		InlineTextExtension ext = getContentExtension( );
		if( ext != null )
		{
			ext.addLine( area );
		}
	}
	
	private void replaceLine( InlineTextArea oldArea, InlineTextArea newArea )
	{
		InlineTextExtension ext = getContentExtension( );
		if( ext != null )
		{
			ext.replaceLine( oldArea, newArea );
		}
	}
	
	private void addLineBreakToExtension( )
	{
		InlineTextExtension ext = getContentExtension( );
		if( ext != null )
		{
			ext.addLineBreak( );
		}
	}
	
	private void addLineBreakToExtension( InlineTextArea area )
	{
		InlineTextExtension ext = getContentExtension( );
		if( ext != null )
		{
			ext.addLineBreak( area );
		}
	}
	
	private InlineTextExtension getContentExtension( )
	{
		if ( context.isFixedLayout( )
				&& context.getEngineTaskType( ) == IEngineTask.TASK_RUN )
		{
			InlineTextExtension ext = (InlineTextExtension) content
					.getExtension( IContent.LAYOUT_EXTENSION );
			if ( ext == null )
			{
				ext = new InlineTextExtension( );
				content.setExtension( IContent.LAYOUT_EXTENSION, ext );
			}
			return ext;
		}
		return null;
	}
	

	private void updateTextContent( )
	{
		if( context.isInHtmlRender( ))
		{
			((ITextContent)content).setText( listener.getSplitText( ) );
		}
	}
	
	protected void close( boolean isLastLine ) throws BirtException
	{
		super.close( isLastLine );
		if ( isLastLine )
		{
			addLineToExtension(this);
			addLineBreakToExtension( this );
		}
		checkDisplayNone( );
	}

	protected boolean checkPageBreak( ) throws BirtException
	{
		boolean ret = false;
		if ( !isInInlineStacking && context.isAutoPageBreak( ) )
		{
			int aHeight = getAllocatedHeight( );
			while ( aHeight + parent.getAbsoluteBP( ) > context.getMaxBP( ) )
			{
				addLineBreakToExtension( );
				if ( !parent.autoPageBreak( ) )
				{
					return false;
				}
				aHeight = getAllocatedHeight( );
				ret = true;
			}
		}
		return ret;
	}

	public SplitResult split( int height, boolean force ) throws BirtException
	{
		if ( force )
		{
			// current line will be the last line in current page.
			InlineTextArea newArea = (InlineTextArea) cloneArea( );
			addLineToExtension( newArea );
			newArea.children.addAll( children );
			children.clear( );
			this.height = 0;
			replaceLine( this, newArea );
			addLineBreakToExtension( newArea );
			return new SplitResult( newArea,
					SplitResult.SPLIT_SUCCEED_WITH_PART );
		}
		else
		{
			addLineBreakToExtension( );
			// current line will go next page.
			return SplitResult.SUCCEED_WITH_NULL;
		}
	}

	@Override
	protected void doReset( )
	{
		// reset the extension information when the inline text is in a header.
		// this prevents inline text page hint being carried over to the next repeated header.
		if ( isHeader( ) )
		{
			content.setExtension( IContent.LAYOUT_EXTENSION, null );
		}
	}
	
	private boolean isHeader( )
	{
		IElement parent = content.getParent( );
		while ( parent != null )
		{
			if ( parent instanceof AbstractBandContent )
			{
				AbstractBandContent band = (AbstractBandContent) parent;
				if ( band.getBandType( ) == IBandContent.BAND_HEADER
						|| band.getBandType( ) == IBandContent.BAND_GROUP_HEADER )
				{
					return true;
				}
			}
			parent = parent.getParent( );
		}
		return false;
	}
}

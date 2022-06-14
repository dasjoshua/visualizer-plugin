package com.sweilab.testvisualizer.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;

public class SampleHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		File fileToOpen = new File("C:\\Users\\jo3da\\runtime-EclipseApplication\\s\\src\\s\\Targettest.java");

		if (fileToOpen.exists() && fileToOpen.isFile()) {
			IFileStore fileStore = EFS.getLocalFileSystem().getStore(fileToOpen.toURI());
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

			try {
				IDE.openEditorOnFileStore( page, fileStore );
			} catch ( PartInitException e ) {
				//Put your exception handler here if you wish to
			}
		} else {
			System.out.println("File does not exist");
		}
		IEditorPart editor = window.getActivePage().getActiveEditor();

		ITextEditor txteditor = editor.getAdapter(ITextEditor.class);
		if (txteditor != null) {
			IDocumentProvider provider = txteditor.getDocumentProvider();
			IDocument document = provider.getDocument(editor.getEditorInput());
		}

		ITextEditor editor1 = (ITextEditor) editor;
		IDocument document = editor1.getDocumentProvider().getDocument(
				editor.getEditorInput());
//				lnr = new LineNumberReader(new FileReader("C:\\Users\\jo3da\\runtime-EclipseApplication\\s\\src\\s\\Targettest.java"));
		String str = null;
		int i = 0;
		if (document != null) {
			IRegion lineInfo = null;
			FileReader fr = null;
			LineNumberReader lnr = null;
		      try {
		          // create new reader
		           fr = new FileReader("C:\\Users\\jo3da\\runtime-EclipseApplication\\s\\src\\s\\Targettest.java");
		          lnr = new LineNumberReader(fr);
		    
		          // read lines till the end of the stream
		          while((str = lnr.readLine())!=null) {
		             i = lnr.getLineNumber();
		             System.out.print("("+i+")");
		             if(str.contains("print"))
		            	 break;
		                   
		             // prints string
		             System.out.println(str);
		          }
		          
		       } catch(Exception e) {
		          // if any error occurs
		          e.printStackTrace();
		       } finally {
		          // closes the stream and releases system resources
		       }
				try {
					lineInfo = document.getLineInformation(i - 1);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			

				System.out.println(lineInfo);
			if (lineInfo != null) {
				editor1.selectAndReveal(lineInfo.getOffset(), lineInfo.getLength());
				//editor1.setHighlightRange(lineInfo.getOffset(), lineInfo.getLength(), true);

			}
		}
		//      IEditorInput input = editor.getEditorInput();
		//      FileEditorInput file = (FileEditorInput)input;
		//      ITextEditor itext = (ITextEditor)editor;
		//      System.out.println("mmmmmmmmmmmmmmmmm"+editor.getSite());
		return null;

	}

}

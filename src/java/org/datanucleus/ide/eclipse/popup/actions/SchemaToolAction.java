/**********************************************************************
Copyright (c) 2004 Erik Bengtson and others. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Contributors:
2014 Andy Jefferson - changed to inherit JavaProjectAction
 ...
 **********************************************************************/
package org.datanucleus.ide.eclipse.popup.actions;

import java.util.List;

import org.datanucleus.ide.eclipse.Plugin;
import org.datanucleus.ide.eclipse.wizard.schematool.SchemaToolWizard;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IActionDelegate;

/**
 * Action invoked when the user selects the popup menu option "SchemaTool" from a Java project.
 */
public class SchemaToolAction extends JavaProjectAction
{
    /**
     * @see IActionDelegate#run(IAction)
     */
    public void run(IAction action)
    {
        List<IJavaProject> javaProjects = getSelectedJavaProjects();

        IJavaProject javaProject = javaProjects.isEmpty() ? null : javaProjects.get(0);
        if (javaProject == null)
        {
            Plugin.logError("Attempt to invoke SchemaTool but no JavaProject selected!");
            return;
        }

        // Instantiate and initializes the wizard
        SchemaToolWizard wizard = new SchemaToolWizard(javaProject);
        if ((getSelection() instanceof IStructuredSelection) || (getSelection() == null))
        {
            wizard.init(getActivePart().getSite().getWorkbenchWindow().getWorkbench(), (IStructuredSelection) getSelection());
        }

        // Instantiates the wizard container with the wizard and opens it
        WizardDialog dialog = new WizardDialog(getActivePart().getSite().getShell(), wizard);
        dialog.create();
        dialog.open();
    }
}
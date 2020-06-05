package commands;

import basemod.devcommands.ConsoleCommand;

public class CommandCommand extends ConsoleCommand {

	public CommandCommand() {
		this.followup.put("import", CommandImport.class);
	}
	
	
	@Override
	protected void execute(String[] arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

}

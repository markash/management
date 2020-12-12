package threesixty.hr.management.client.employee;

import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;

import threesixty.hr.management.client.employee.ProjectForm.MainBox.CancelButton;
import threesixty.hr.management.client.employee.ProjectForm.MainBox.GroupBox;
import threesixty.hr.management.client.employee.ProjectForm.MainBox.OkButton;


public class ProjectForm extends AbstractForm {
	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("Project");
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public GroupBox getGroupBox() {
		return getFieldByClass(GroupBox.class);
	}

	public OkButton getOkButton() {
		return getFieldByClass(OkButton.class);
	}

	public CancelButton getCancelButton() {
		return getFieldByClass(CancelButton.class);
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {
		@Order(1000)
		public class GroupBox extends AbstractGroupBox {

//			@Order(1000)
//			@ClassId("e06efe30-25db-446c-848f-22935dcff376")
//			public class TileField extends AbstractTileField<TileGrid> {
//
//				@Override
//				protected boolean getConfiguredLabelVisible() {
//					return false;
//				}
//
//				@Override
//				protected int getConfiguredGridW() {
//					return FULL_WIDTH;
//				}
//
//				@Override
//				protected void execInitField() {
//					
//				}
//
//				@ClassId("0cd4de91-68d0-4a1d-b123-5006b566481d")
//				public class TileGrid extends AbstractTileGrid<ICustomTile> {
//
//					@Override
//					protected void initConfig() {
//						super.initConfig();
//						TileGridMenuType.updateMenuVisibilitiesForTiles(this);
//
//						addPropertyChangeListener((event) -> {
//							if (event.getPropertyName().equals(ITileGrid.PROP_TILES)
//									|| event.getPropertyName().equals(ITileGrid.PROP_FILTERED_TILES)) {
//								updateStatus();
//							}
//							if (event.getPropertyName().equals(ITileGrid.PROP_FILTERED_TILES)) {
//								updateHasCustomTiles();
//							}
//						});
//					}
//
//					@Override
//					protected void execTilesSelected(List<ICustomTile> tiles) {
//						super.execTilesSelected(tiles);
//						TileGridMenuType.updateMenuVisibilitiesForTiles(this);
//						updateStatus();
//					}
//
//
//					@ClassId("ef2f2d9a-c0cf-4f52-b057-cb408a82b4d0")
//					public class SimpleTile1 extends AbstractCustomTile {
//						@Override
//						protected String getConfiguredLabel() {
//							return "Tile 1";
//						}
//					}
//
//					@ClassId("35c830af-96dd-47ad-a494-eed258318938")
//					public class SimpleTile2 extends AbstractCustomTile {
//						@Override
//						protected String getConfiguredLabel() {
//							return "Tile 2";
//						}
//					}
//
//					@ClassId("27e2d10a-9a1c-4ed9-bc18-4b5467da8e98")
//					public class SimpleTile3 extends AbstractCustomTile {
//						@Override
//						protected String getConfiguredLabel() {
//							return "Tile 3";
//						}
//					}
//
//					@ClassId("79db80d5-173e-48b3-9445-1db48dd41f12")
//					public class SimpleTile4 extends AbstractCustomTile {
//						@Override
//						protected String getConfiguredLabel() {
//							return "Tile 4";
//						}
//					}
//
//					@ClassId("074b1675-16ef-481e-8f65-174341ed4b5c")
//					public class SimpleTile5 extends AbstractCustomTile {
//						@Override
//						protected String getConfiguredLabel() {
//							return "Tile 5";
//						}
//					}
//				}
//			}

		}

		@Order(2000)
		public class OkButton extends AbstractOkButton {
		}

		@Order(3000)
		public class CancelButton extends AbstractCancelButton {
		}
	}

	public void startModify() {
		startInternalExclusive(new ModifyHandler());
	}

	public void startNew() {
		startInternal(new NewHandler());
	}

	public class NewHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
		}

		@Override
		protected void execStore() {
		}
	}

	public class ModifyHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
		}

		@Override
		protected void execStore() {
		}
	}
}

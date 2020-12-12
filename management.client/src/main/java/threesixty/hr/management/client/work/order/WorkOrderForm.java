package threesixty.hr.management.client.work.order;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.booleanfield.AbstractBooleanField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.htmlfield.AbstractHtmlField;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import threesixty.hr.management.client.common.column.AbstractIdColumn;
import threesixty.hr.management.client.common.field.AbstractDateField;
import threesixty.hr.management.client.work.order.WorkOrderForm.MainBox.BusinessCaseBox;
import threesixty.hr.management.client.work.order.WorkOrderForm.MainBox.BusinessCaseBox.EpicDescriptionField;
import threesixty.hr.management.client.work.order.WorkOrderForm.MainBox.BusinessCaseBox.EpicOwnerField;
import threesixty.hr.management.client.work.order.WorkOrderForm.MainBox.BusinessCaseBox.KeyStakeholderField;
import threesixty.hr.management.client.work.order.WorkOrderForm.MainBox.CancelButton;
import threesixty.hr.management.client.work.order.WorkOrderForm.MainBox.GeneralBox.DescriptionField;
import threesixty.hr.management.client.work.order.WorkOrderForm.MainBox.GeneralBox.FinanceBox;
import threesixty.hr.management.client.work.order.WorkOrderForm.MainBox.GeneralBox.FinanceBox.CapitalizeField;
import threesixty.hr.management.client.work.order.WorkOrderForm.MainBox.GeneralBox.FinanceBox.EstimateField;
import threesixty.hr.management.client.work.order.WorkOrderForm.MainBox.GeneralBox.NameField;
import threesixty.hr.management.client.work.order.WorkOrderForm.MainBox.GeneralBox.WorkOrderTypeField;
import threesixty.hr.management.client.work.order.WorkOrderForm.MainBox.OkButton;
import threesixty.hr.management.client.work.order.WorkOrderForm.MainBox.RolesBox;
import threesixty.hr.management.client.work.order.WorkOrderForm.MainBox.RolesBox.RolesField;
import threesixty.hr.management.client.work.order.WorkOrderForm.MainBox.ScheduleBox;
import threesixty.hr.management.client.work.order.WorkOrderForm.MainBox.ScheduleBox.ActualEndField;
import threesixty.hr.management.client.work.order.WorkOrderForm.MainBox.ScheduleBox.ActualStartField;
import threesixty.hr.management.client.work.order.WorkOrderForm.MainBox.ScheduleBox.ScheduledEndField;
import threesixty.hr.management.client.work.order.WorkOrderForm.MainBox.ScheduleBox.ScheduledStartField;
import threesixty.hr.management.client.work.order.WorkOrderForm.MainBox.Spacer01Field;
import threesixty.hr.management.client.work.order.WorkOrderTablePage.Table.IdColumn;
import threesixty.hr.management.shared.services.party.internalOrganization.InternalOrganizationLookupCall;
import threesixty.hr.management.shared.services.party.person.PersonLookupCall;
import threesixty.hr.management.shared.services.work.IWorkManager;
import threesixty.hr.management.shared.services.work.order.WorkOrderTypeLookupCall;
import threesixty.hr.management.shared.work.order.WorkOrderFormData;

@FormData(value = WorkOrderFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class WorkOrderForm extends AbstractForm {
	
	private Long id;
	
	@FormData
	public Long getId() {
		return id;
	}
	
	@FormData
	public void setId(
			final Long id) {
		
		this.id = id;
	}
	
	public WorkOrderForm withId(
			final Long id) {
	
		setId(id);
		return this;
	}
	
	private String calculateSubTitle() {
	
		return getNameField().getValue();
	}
	
	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("WorkOrder");
	}
	
	@Override
	protected int getConfiguredDisplayHint() {
		return IForm.DISPLAY_HINT_VIEW;
	}
	
	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public RolesBox getRolesBox() {
		return getFieldByClass(RolesBox.class);
	}

	public ScheduledStartField getScheduledStartField() {
		return getFieldByClass(ScheduledStartField.class);
	}

	public ScheduledEndField getScheduledEndField() {
		return getFieldByClass(ScheduledEndField.class);
	}

	public ActualStartField getActualStartField() {
		return getFieldByClass(ActualStartField.class);
	}

	public ActualEndField getActualEndField() {
		return getFieldByClass(ActualEndField.class);
	}

	public CapitalizeField getCapitalizeField() {
		return getFieldByClass(CapitalizeField.class);
	}

	public EstimateField getEstimateField() {
		return getFieldByClass(EstimateField.class);
	}

	public RolesField getRolesField() {
		return getFieldByClass(RolesField.class);
	}

	public ScheduleBox getScheduleBox() {
		return getFieldByClass(ScheduleBox.class);
	}

	public FinanceBox getFinanceBox() {
		return getFieldByClass(FinanceBox.class);
	}

	public DescriptionField getDescriptionField() {
		return getFieldByClass(DescriptionField.class);
	}

	public BusinessCaseBox getBusinessCaseBox() {
		return getFieldByClass(BusinessCaseBox.class);
	}

	public EpicOwnerField getEpicOwnerField() {
		return getFieldByClass(EpicOwnerField.class);
	}

	public KeyStakeholderField getKeyStakeholderField() {
		return getFieldByClass(KeyStakeholderField.class);
	}

	public EpicDescriptionField getEpicDescriptionField() {
		return getFieldByClass(EpicDescriptionField.class);
	}

	public WorkOrderTypeField getWorkOrderTypeField() {
		return getFieldByClass(WorkOrderTypeField.class);
	}

	public Spacer01Field getBlankField() {
		return getFieldByClass(Spacer01Field.class);
	}

	public NameField getNameField() {
		return getFieldByClass(NameField.class);
	}

	public OkButton getOkButton() {
		return getFieldByClass(OkButton.class);
	}

	public CancelButton getCancelButton() {
		return getFieldByClass(CancelButton.class);
	}

	
//	@Override
//	protected void execFormActivated() {
//
//		WorkOrderFormData formData = new WorkOrderFormData();
//		exportFormData(formData);
//		
//		formData = BEANS.get(IWorkManager.class).prepareModify(formData);
//		importFormData(formData);
//	}
//	
	
	
	@Order(1000)
	public class MainBox extends AbstractGroupBox {
	
		@Order(1000)
		public class GeneralBox extends AbstractGroupBox {
		
			@Override
			protected int getConfiguredGridW() { return 1; }
			
			@Override
			protected String getConfiguredLabel() { return TEXTS.get("General"); }
			
			@Order(1000)
			public class NameField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Name");
				}

				@Override
				protected int getConfiguredMaxLength() {
					return 128;
				}
			}

			@Order(2000)
			public class WorkOrderTypeField extends AbstractSmartField<String> {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("WorkOrderType");
				}
				
				@Override
				protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
					return WorkOrderTypeLookupCall.class;
				}
			}

			@Order(3000)
			public class FinanceBox extends AbstractSequenceBox {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Finance");
				}

				@Override
				protected boolean getConfiguredAutoCheckFromTo() {
					return false;
				}
				
				@Order(6000)
				public class CapitalizeField extends AbstractBooleanField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Capitalize");
					}
				}

				@Order(7000)
				public class EstimateField extends AbstractBooleanField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Estimate");
					}
				}
			}

			@Order(3000)
			public class DescriptionField extends AbstractHtmlField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Description");
				}
			}	
		}
		
		@Order(3000)
		public class ScheduleBox extends AbstractGroupBox {
			
			@Override
			protected int getConfiguredGridW() { return 1; }
			
			@Override
			protected String getConfiguredLabel() { return TEXTS.get("Schedule"); }
			
			@Order(2000)
			public class ScheduledStartField extends AbstractDateField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ScheduledStart");
				}
			}

			@Order(3000)
			public class ScheduledEndField extends AbstractDateField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ScheduledEnd");
				}
			}

			@Order(4000)
			public class ActualStartField extends AbstractDateField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ActualStart");
				}
			}

			@Order(5000)
			public class ActualEndField extends AbstractDateField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ActualEnd");
				}
			}
		}
		
		@Order(4000)
		public class RolesBox extends AbstractGroupBox {
			
			@Override
			protected int getConfiguredGridW() { return 1; }
			
			@Override
			protected String getConfiguredLabel() { return TEXTS.get("Roles"); }

			@Override
			protected int getConfiguredGridColumnCount() { return 1; }
			
			@Order(1000)
			public class RolesField extends AbstractTableField<RolesField.Table> {

				@Override
				protected boolean getConfiguredLabelVisible() { return false; }
				
				public class Table extends AbstractTable {

					@Override
					protected boolean getConfiguredAutoResizeColumns() { return true; }
					
					public AssignedToNameColumn getAssignedToColumn() {
						return getColumnSet().getColumnByClass(AssignedToNameColumn.class);
					}
					
					public EffectiveFromColumn getEffectiveFromColumn() {
						return getColumnSet().getColumnByClass(EffectiveFromColumn.class);
					}

					public EffectiveToColumn getEffectiveToColumn() {
						return getColumnSet().getColumnByClass(EffectiveToColumn.class);
					}

					public AssignedToIdColumn getAssignedToIdColumn() {
						return getColumnSet().getColumnByClass(AssignedToIdColumn.class);
					}

					public RoleColumn getRoleColumn() {
						return getColumnSet().getColumnByClass(RoleColumn.class);
					}

					public IdColumn getIdColumn() {
						return getColumnSet().getColumnByClass(IdColumn.class);
					}

					
					@Order(1000)
					public class AssignedToNameColumn extends AbstractStringColumn {
						@Override
						protected String getConfiguredHeaderText() {
							return TEXTS.get("AssignedTo");
						}

						@Override
						protected int getConfiguredWidth() {
							return 25;
						}
					}

					@Order(2000)
					public class RoleColumn extends AbstractStringColumn {
						@Override
						protected String getConfiguredHeaderText() {
							return TEXTS.get("Role");
						}

						@Override
						protected int getConfiguredWidth() {
							return 100;
						}
					}

					@Order(3000)
					public class EffectiveFromColumn extends AbstractDateColumn {
						@Override
						protected String getConfiguredHeaderText() {
							return TEXTS.get("From");
						}
						
						@Override
						protected int getConfiguredWidth() {
							return 25;
						}
					}

					@Order(4000)
					public class EffectiveToColumn extends AbstractDateColumn {
						@Override
						protected String getConfiguredHeaderText() {
							return TEXTS.get("To");
						}

						@Override
						protected int getConfiguredWidth() {
							return 25;
						}
					}
					
					@Order(110000)
					public class AssignedToIdColumn extends AbstractIdColumn {
						
						@Override
						protected boolean getConfiguredDisplayable() { return false; }
					}
				}
				
				@Override
				protected int getConfiguredGridH() {
					return 6;
				}
			}
		}
		
		@Order(2000)
		public class BusinessCaseBox extends AbstractGroupBox {

			@Override
			protected int getConfiguredGridW() { return 1; }
			
			@Override
			protected String getConfiguredLabel() { return TEXTS.get("BusinessCase"); }

			
			@Order(5000)
			public class FunnelEntryField extends AbstractDateField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("FunnelEntryDate");
				}
			}

			@Order(6000)
			public class EpicOwnerField extends AbstractSmartField<Long> {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("EpicOwner");
				}
				
				@Override
				protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
					return PersonLookupCall.class;
				}
			}

			@Order(7000)
			public class KeyStakeholderField extends AbstractSmartField<Long> {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("KeyStakeholder");
				}
				
				@Override
				protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
					return InternalOrganizationLookupCall.class;
				}
			}

			@Order(8000)
			public class EpicDescriptionField extends AbstractHtmlField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("EpicDescription");
				}
				
				@Override
				protected int getConfiguredGridH() {
					return 3;
				}
			}	
		}

		@Order(5000)
		public class Spacer01Field extends AbstractStringField {
			@Override
			protected boolean getConfiguredVisible() {
				
				return false;
			}
		}
		
		
		
		@Order(2000)
		public class OkButton extends AbstractOkButton { }

		@Order(3000)
		public class CancelButton extends AbstractCancelButton {  }
	}

	public void startModify() {
		startInternalExclusive(new ModifyHandler());
	}

	public void startNew() {
		startInternal(new NewHandler());
	}

	public class NewHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() { }

		@Override
		protected void execStore() {
		}
	}

	public class ModifyHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
			
			WorkOrderFormData formData = new WorkOrderFormData();
			exportFormData(formData);
			
			formData = BEANS.get(IWorkManager.class).prepareModify(formData);
			importFormData(formData);
			
			getForm().setSubTitle(calculateSubTitle());
			
		}

		@Override
		protected void execStore() {
			
			WorkOrderFormData formData = new WorkOrderFormData();
			exportFormData(formData);
			
			BEANS.get(IWorkManager.class).executeModify(formData);
		}
	}
}

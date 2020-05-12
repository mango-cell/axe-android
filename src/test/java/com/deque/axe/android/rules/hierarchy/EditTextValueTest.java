package com.deque.axe.android.rules.hierarchy;

import com.deque.axe.android.constants.AxeStatus;
import com.deque.axe.android.wrappers.AxeProps;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class EditTextValueTest {

  @Mock
  private AxeProps axeProps;

  private EditTextValue subject;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    subject = new EditTextValue();
  }

  @Test
  public void switchClass_isApplicable() {
    when(axeProps.get(AxeProps.Name.CLASS_NAME, String.class)).thenReturn("android.widget.CheckBox");

    assertFalse(subject.isApplicable(axeProps));
  }

  @Test
  public void editTextClass_isApplicable() {
    when(axeProps.get(AxeProps.Name.CLASS_NAME, String.class)).thenReturn("android.widget.EditText");

    assertTrue(subject.isApplicable(axeProps));
  }

  @Test
  public void emptyContentDescription_runRule() {
    when(axeProps.get(AxeProps.Name.CONTENT_DESCRIPTION, String.class)).thenReturn("");

    assertEquals(subject.runRule(axeProps), AxeStatus.PASS);
  }

  @Test
  public void nonEmptyContentDescription_runRule() {
    when(axeProps.get(AxeProps.Name.CONTENT_DESCRIPTION, String.class)).thenReturn("Content Description");

    assertEquals(subject.runRule(axeProps), AxeStatus.FAIL);
  }
}
import React, { Component } from 'react'
import CKEditor from "react-ckeditor-component";

export default class Example extends Component {
  state = {
    content: 'content',
    config: {
      toolbarGroups: [{
        "name": "basicstyles",
        "groups": ["basicstyles"]
      },
        {
          "name": "links",
          "groups": ["links"]
        },
        {
          "name": "paragraph",
          "groups": ["list", "blocks"]
        },
        {
          "name": "document",
          "groups": ["mode"]
        },
        {
          "name": "insert",
          "groups": ["insert"]
        },
        {
          "name": "styles",
          "groups": ["styles"]
        },
        /*      {
                "name": "about",
                "groups": ["about"]
              }*/
      ],
      // Remove the redundant buttons from toolbar groups defined above.
      removeButtons: 'Underline,Strike,Subscript,Superscript,Anchor,Styles,Specialchar'
    },
  };

  updateContent = (newContent) => {
    this.setState({
      content: newContent
    })
  };
  onChange = (evt) => {
    console.log("onChange fired with event info: ", evt);
    this.setState({
      content: evt.editor.getData()
    })
    this.props.onChangeContent(this.state.content)
  };
  onBlur = (evt) => {
    console.log("onBlur event called with event info: ", evt);
  };
  afterPaste = (evt) => {
    console.log("afterPaste event called with event info: ", evt);
  };

  render() {
    return (
      <div style={{width: '100%'}}>
        <CKEditor
          activeClass="p10"
          content={this.state.content}
          config={this.state.config}
          events={{
            "blur": this.onBlur,
            "afterPaste": this.afterPaste,
            "change": this.onChange
          }}
        />
      </div>
    );
  }
}
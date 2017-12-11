import React, {Component} from 'react';
import axios from 'axios';
// import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
// import {myFilesSelected} from "../actions/activeDropBoxWindow";
import {Button, Modal} from 'react-bootstrap';

class ListGroups extends Component {
    constructor(props) {
        super(props);
        this.state = {
            coowner: '',
            showModalShare: false
        }
        this.openModalShare = this.openModalShare.bind(this);
        this.closeModalShare = this.closeModalShare.bind(this);
    }

/*    handleDelete = () => {
        return axios.post('http://localhost:8080/files/delete', {
            "_id": this.props.item._id,
            "owner": this.props.item.owner
        })
            .then((res) => {
                if (res.status === 200) {
                    let myFiles = this.props.myFiles;
                    myFiles = myFiles.filter(myFile => myFile._id !== this.props.item._id);
                    this.props.myFilesSelected(myFiles);
                    console.log(this.props.item.fileName, " deleted");
                }
                else {
                    console.log("server returned error");
                }
            })
            .catch((err) => {
                console.log("could not connect server");
            });
    };*/
    closeModalShare() {
        this.setState({
            ...this.state,
            showModalShare: false
        });
    };

    openModalShare() {
        this.setState({showModalShare: true});
    };

    handleAddCoowner = ()=>{
        return axios.post('http://localhost:8080/files/share', {
            "_id": this.props.item._id,
            "owner": this.props.item.owner,
            "coowner":this.state.coowner
        })
            .then((res) => {
                if (res.status === 200) {
                    alert("file has been shared with user");
                    console.log("file shared with the user");
                }
                else {
                    console.log("server returned error");
                }
            })
            .catch((err) => {
                console.log("could not connect server");
            });
    };


    render() {
        // const file = this.props;
        return (
            <div>
                <Modal show={this.state.showModalShare} onHide={() => this.closeModalShare()}>
                    <Modal.Header closeButton>
                        <Modal.Title>Give username</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        {/*{messageDivLogin}*/}
                        <div className="row justify-content-md-center">
                            <div className="form-group row">
                                <label className="col-md form-label">
                                    All Fields are Mandatory
                                </label>
                            </div>
                            <div className="form-group row">
                                <label className=" col-md-4 col-form-label">coowner username</label>
                                {/*<label className="form-label">coowner username</label>*/}
                                <div className=" col-sm-8">
                                    <input type="text" className="form-control"
                                           value={this.state.coowner}
                                           onChange={(event) => {
                                               this.setState({...this.state, coowner: event.target.value});
                                           }} required/>
                                </div>
                            </div>
                        </div>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button type="button" onClick={()=>{this.handleAddCoowner()}}>Submit</Button>
                        <Button onClick={() => this.closeModalShare()}>Close</Button>
                    </Modal.Footer>
                </Modal>
                <div className="row justify-content-md-center">
                    <div className="col-md-12">
                        <div className="card col-md-12">
                            <div className="card-body">
                                <div className="col-md-5">
                                    {this.props.item.fileName}
                                </div>
                                <div className="col-md-6" align="left">
                                    <button type="button" className="btn btn-primary"
                                        // onClick={this.handleDownload(this.props.item.path)}>
                                            onClick={() => this.props.handleDownload(this.props.item.path)}>
                                        Download
                                    </button>
                                    {/*<button type="button"
                                            onClick={() => {
                                                this.handleDelete();
                                            }}
                                            className="btn btn-primary" style={{marginLeft: 10}}>
                                        Delete
                                    </button>*/}
                                    <button type="button"
                                            onClick={() => {
                                                this.openModalShare();
                                            }}
                                            className="btn btn-primary" style={{marginLeft: 10}}>
                                        Share File
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

/*function mapStateToProps(state) {
    return {
        myFiles: state.activeWindow.myFiles
    }
}*/

/*function matchDispatchToProps(dispatch) {
    return bindActionCreators({
        myFilesSelected: myFilesSelected
    }, dispatch);
}*/

// export default connect(mapStateToProps, matchDispatchToProps)(ListGroups);
export default ListGroups;
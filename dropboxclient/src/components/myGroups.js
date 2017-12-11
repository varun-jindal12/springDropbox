import React, {Component} from 'react';
import {connect} from 'react-redux';
import ListGroups from './listGroups';

class MyGroup extends Component {
    handleDownload = (path) => {
        console.log("path is");
        console.log(path);
        window.open(path);
    }

    render() {
        if (this.props.myGroups.length !== 0) {
            return (
                <div style={{marginTop: "8%"}}>
                    <h2>My Shared Files</h2>
                    <div className="row justify-content-md-center">
                        <div className="card col-md-12">
                            <div className="card-body">
                                {
                                    this.props.myGroups.map((file, index) => {
                                        return (
                                            <ListGroups
                                                key={index}
                                                item={file}
                                                handleDownload={this.handleDownload}
                                            />
                                        );
                                    })
                                }
                            </div>
                        </div>
                    </div>
                </div>
            )
        }
        else {
            return (
                <div style={{marginTop: "8%"}}>
                    <h2>My File</h2>
                    <div className="row justify-content-md-center">
                        <div className="card col-md-12">
                            <div className="card-body">
                                <span className="text text-danger">No files found</span>
                            </div>
                        </div>
                    </div>
                </div>
            )

        }
    }
}

function mapStateToProps(state) {
    return {
        userData: state.activeWindow.userData.userData,
        myGroups: state.activeWindow.myGroups

    }
}

export default connect(mapStateToProps)(MyGroup);
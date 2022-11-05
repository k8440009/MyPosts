import Button from "react-bootstrap/Button";
import 'bootstrap/dist/css/bootstrap.min.css'
import React from "react";

function PostSave () {
    return (
        <div className="container-sm">
            <div>
                <form>
                    <div>
                        <label>제목</label>
                        <input type="text" className="form-control" id="title" placeholder="제목을 입력하세요"/>
                    </div>

                    <div>

                    </div>

                    <div>

                    </div>
                </form>
            </div>
            <Button>등록</Button>
            <Button>취소</Button>
        </div>
    );
}

export default PostSave;
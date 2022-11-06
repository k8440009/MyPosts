import Button from "react-bootstrap/Button";
import 'bootstrap/dist/css/bootstrap.min.css'
import React, {useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

function PostSave () {
    const [title, setTitle] = useState('')
    const [author, setAuthor] = useState('')
    const [content, setContent] = useState('')

    const navigate = useNavigate();

    const onChangeTitle = (e) => {
        setTitle(e.target.value)
    }

    const onChangeAuthor = (e) => {
        setAuthor(e.target.value)
    }

    const onChangeContent = (e) => {
        setContent(e.target.value)
    }

    function saveFunction() {
        const data = {
            title : title,
            author : author,
            content : content
        };

        axios.post('/api/v1/posts', data)
            .then(function (response) {
                console.log(JSON.stringify(response))
                alert("글이 등록되었습니다.")

                navigate('/')
            })
            .catch(function (error) {
                alert(JSON.stringify(error))
            })
        ;
    }

    function handleCancel() {
        navigate('/')
    }


    return (
        <div className="container-sm">
            <div>
                <form>
                    <div className="form-group">
                        <label htmlFor="title">제목</label>
                        <input onChange={onChangeTitle} value={title} type="text" className="form-control" id="title" placeholder="제목을 입력하세요"/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="author"> 작성자 </label>
                        <input onChange={onChangeAuthor} value={author} åtype="text" className="form-control" id="author" placeholder="작성자를 입력하세요"/>
                    </div>
                    <div>
                        <label htmlFor="content"> 내용 </label>
                        <textarea onChange={onChangeContent} value={content} className="form-control" id="content" placeholder="내용을 입력하세요"></textarea>
                    </div>
                </form>
            </div>
            <Button onClick={saveFunction}>등록</Button>
            <Button onClick={handleCancel} variant="secondary">취소</Button>
        </div>
    );
}

export default PostSave;